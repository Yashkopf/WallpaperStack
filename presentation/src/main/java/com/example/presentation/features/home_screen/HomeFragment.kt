package com.example.presentation.features.home_screen

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.adapters.WallpaperAdapter
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.features.bottom_sheet_details.BottomSheetDetailsFragment
import com.example.presentation.features.bottom_sheet_settings.BottomSheetSettingsFragment
import com.example.presentation.features.detail_screen.DetailFragment
import com.example.presentation.utils.Empty
import com.example.presentation.utils.MarginItemDecoration
import com.example.presentation.utils.getCustomColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


internal class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var binding: FragmentHomeBinding? = null
    private var recyclerViewState: Parcelable? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var buttons: List<TextView>? = null
    private var adapter: WallpaperAdapter? = null
    private var searchView: SearchView? = null
    private var currentColor: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        renderRecyclerState()
        connectivityStatus()
        searchWallpapers()
        changeSorting()
        initObservers()
        swipeToRefresh()
        launchBottomSheetSettingsFragment()
        onScrollListenerSearchBar()
        onScrollListenerFAB()
        showNumberOfWallpapers()
    }

    private fun initObservers() {

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.wallpapersList.collectLatest { pagingData ->
                adapter?.submitData(pagingData)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.itemsCount.collect { value ->
                if (value == null) return@collect
                binding?.tvCountResults?.text = getString(R.string.count_results, value)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.buttonState.collectLatest { (oldValue, newValue) ->
                changeBackgroundSorting(newValue, oldValue)
                buttons?.get(newValue)?.backgroundTintList =
                    ColorStateList.valueOf(
                        requireContext()
                            .getCustomColor(R.color.select_button_state)
                    )
                if (oldValue != -1) {
                    buttons?.get(oldValue)?.backgroundTintList =
                        ColorStateList.valueOf(
                            requireContext()
                                .getCustomColor(R.color.default_button_state)
                        )
                }
            }
        }
    }

    private fun applyAlphaToColor(color: Int, alpha: Int): Int {
        val rgb = color and 0x00FFFFFF
        val alphaShifted = (alpha.coerceIn(0, 255) shl 24)
        return rgb or alphaShifted
    }

    private fun changeBackgroundSorting(newColor: Int, oldColor: Int) {

        val newBackgroundColor = getBackgroundColor(newColor)
        val oldBackgroundColor = getBackgroundColor(oldColor)

        val fromColor = ContextCompat.getColor(requireContext(), oldBackgroundColor)
        val toColor = ContextCompat.getColor(requireContext(), newBackgroundColor)

        val drawable = binding?.backgroundGradient?.background?.mutate() ?: return
        val wrapped = DrawableCompat.wrap(drawable)

        val colorAnimation =
            ValueAnimator.ofObject(
                ArgbEvaluator(), applyAlphaToColor(fromColor, 77),
                applyAlphaToColor(toColor, 77)
            )
        if (currentColor != newColor) {
            colorAnimation.duration = 1500
        }

        colorAnimation.addUpdateListener { animator ->
            val animatedColor = animator.animatedValue as Int
            DrawableCompat.setTint(wrapped, animatedColor)
        }
        colorAnimation.start()
        currentColor = newColor
    }

    private fun getBackgroundColor(index: Int): Int {
        return when (index) {
            0 -> R.color.date_added_sorting
            1 -> R.color.hot_sorting
            2 -> R.color.favorite_sorting
            3 -> R.color.random_sorting
            4 -> R.color.views_sorting
            else -> R.color.date_added_sorting
        }
    }

    private fun initRecyclerView() {
        recyclerView = binding?.rvWallpapers
        gridLayoutManager =
            GridLayoutManager(requireContext(), WALLPAPERS_GRID_SPAN_COUNT)

        adapter = WallpaperAdapter(
            onItemClick = { item ->
                launchDetailFragment(item)
            },
            onItemLongClick = { id ->
                launchBottomSheetDetailsFragment(id)
            }
        )

        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.adapter = adapter
        recyclerView?.addItemDecoration(
            MarginItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.margin_medium
                )
            )
        )
    }

    override fun onResume() {
        super.onResume()
        recyclerView?.post {
            recyclerViewState?.let {
                recyclerView?.layoutManager?.onRestoreInstanceState(it)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        recyclerViewState = recyclerView?.layoutManager?.onSaveInstanceState()
    }

    private fun launchDetailFragment(item: Parcelable) {

        val args = DetailFragment.Companion.makeArgs(item)
        findNavController().navigate(
            R.id.detailFragment,
            args,
            navOptions {
                anim {
                    popExit = R.anim.slide_right
                    enter = R.anim.zoom_enter
                    exit = R.anim.zoom_exit
                }
            }
        )
    }

    private fun launchBottomSheetDetailsFragment(id: String) {
        BottomSheetDetailsFragment.Companion.create(id).show(parentFragmentManager, null)
    }

    private fun launchBottomSheetSettingsFragment() {
        binding?.fabSettings?.setOnClickListener { v ->
            BottomSheetSettingsFragment(
                onChangePurity = { purity ->
                    viewModel.updatePurity(purity)
                },
                onChangeCategory = { category ->
                    viewModel.updateCategory(category)
                }
            ).show(parentFragmentManager, null)
        }
    }

    private fun onScrollListenerFAB() {

        val recycler = binding?.rvWallpapers
        val floatActionButton = binding?.fabSettings
        var offset = Empty.INT

        recycler?.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (floatActionButton == null) return@setOnScrollChangeListener

            val layoutMargin = floatActionButton.marginBottom
            val maxOffset = (floatActionButton.height + layoutMargin)
            offset = (offset - oldScrollY / 2).coerceIn(Empty.INT, maxOffset)

            floatActionButton.translationY = offset.toFloat()
            if (offset > maxOffset) floatActionButton.visibility = View.GONE
            if (offset < maxOffset) floatActionButton.visibility = View.VISIBLE

        }
    }

    private fun onScrollListenerSearchBar() {
        val recycler = binding?.rvWallpapers
        val searchView = binding?.searchLayout
        var currentHeight = Empty.INT

        searchView?.post {
            currentHeight = searchView.height
            val searchViewHeight = searchView.height

            recycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int,
                ) {
                    super.onScrolled(recyclerView, dx, dy)
                    val minHeight = Empty.INT
                    val maxHeight = searchViewHeight

                    currentHeight -= dy.div(2)
                    currentHeight = currentHeight.coerceIn(minHeight, maxHeight)

                    val layoutParams = searchView.layoutParams
                    layoutParams.height = currentHeight
                    searchView.layoutParams = layoutParams
                }
            })
        }
    }

    private fun showNumberOfWallpapers() {

        val searchView = binding?.searchQuery
        val resultsCountView = binding?.tvCountResults

        searchView?.post {

            val searchViewLocation = IntArray(2)
            searchView.getLocationOnScreen(searchViewLocation)

            val searchBottomY = searchViewLocation[1] + searchView.height

            resultsCountView?.x = searchView.x
            resultsCountView?.y = searchBottomY + 8f
        }

        resultsCountView?.post {
            resultsCountView.animate().alpha(1f).setDuration(200).start()
            resultsCountView.postDelayed({
                resultsCountView.animate().alpha(0f).setDuration(500).withEndAction {
                    resultsCountView.visibility = View.GONE
                }.start()
            }, 4000)
        }
    }

    private fun changeSorting() {

        buttons = listOfNotNull(
            binding?.dateAdded,
            binding?.hot,
            binding?.favorite,
            binding?.random,
            binding?.views
        )

        buttons?.forEachIndexed { index, button ->
            button.setOnClickListener { view ->
                viewModel.sortWallpapers(index)
                showNumberOfWallpapers()
            }
        }
    }

    private fun searchWallpapers() {

        searchView?.clearFocus()
        searchView = binding?.searchQuery
        val clearButton =
            searchView?.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchWallpapers(query.toString().trim())
                binding?.rvWallpapers?.smoothScrollToPosition(Empty.INT)
                viewModel.sortWallpapers(Empty.INT)
                searchView?.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        clearButton?.setOnClickListener { v ->
            viewModel.searchWallpapers(Empty.STRING)
            searchView?.clearFocus()
            searchView?.setQuery(null, false)
        }
    }

    private fun connectivityStatus() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.connectivity.collectLatest { isOnline ->
                connectivityStateColor(isOnline)
                if (isOnline) {
                    binding?.connectionView?.animate()
                        ?.alpha(Empty.FLOAT)
                        ?.setDuration(3000)
                        ?.withEndAction { binding?.connectionView?.visibility = View.GONE }
                        ?.start()
                    adapter?.refresh()
                } else {
                    binding?.connectionView?.animate()?.cancel()
                    binding?.connectionView?.clearAnimation()
                    binding?.connectionView?.alpha = ALPHA_ON
                    binding?.connectionView?.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun connectivityStateColor(isOnline: Boolean) {
        val connectionViewText = binding?.tvConnectionState
        val connectionView = binding?.connectionView
        if (isOnline) {
            connectionView?.background?.setTint(getCustomColor(R.color.connection_successful))
            connectionViewText?.text = "Connection successful!"
        } else {
            connectionView?.background?.setTint(getCustomColor(R.color.connection_failure))
            connectionViewText?.text = "Check internet connection!"
        }
    }

    private fun renderRecyclerState() {
        adapter?.addLoadStateListener { states ->
            when (states.refresh) {
                is LoadState.Error -> Toast.makeText(
                    requireContext(),
                    "Error loading",
                    Toast.LENGTH_LONG
                ).show()

                LoadState.Loading -> showShimmer()
                is LoadState.NotLoading -> hideShimmer()
            }
        }
    }

    private fun showShimmer() {
        binding?.shimmerLayout?.startShimmer()
        binding?.swipeToRefreshLayout?.isEnabled = false
    }

    private fun hideShimmer() {
        binding?.shimmerLayout?.apply {
            stopShimmer()
            visibility = View.GONE
        }
        binding?.swSortButtons?.visibility = View.VISIBLE
        binding?.rvWallpapers?.visibility = View.VISIBLE
        binding?.searchLayout?.visibility = View.VISIBLE
        binding?.backgroundGradient?.visibility = View.VISIBLE
        binding?.tvCountResults?.visibility = View.VISIBLE
        binding?.swipeToRefreshLayout?.isEnabled = true
    }

    private fun swipeToRefresh() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            binding?.swipeToRefreshLayout?.setOnRefreshListener {
                adapter?.refresh()
                binding?.swipeToRefreshLayout?.isRefreshing = false
            }
        }
        handler.postDelayed(
            runnable, 300L
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        const val ALPHA_ON = 1f
        const val WALLPAPERS_GRID_SPAN_COUNT = 2
    }
}
