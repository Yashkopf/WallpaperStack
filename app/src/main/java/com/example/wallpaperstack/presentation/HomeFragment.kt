package com.example.wallpaperstack.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperstack.R
import com.example.wallpaperstack.databinding.FragmentHomeBinding
import com.example.wallpaperstack.presentation.adapters.WallpaperAdapter
import com.example.wallpaperstack.presentation.utils.MarginItemDecoration
import com.example.wallpaperstack.presentation.utils.getCustomColor
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModel: WallpaperViewModel by viewModel()

    private var binding: FragmentHomeBinding? = null

    private var recyclerViewState: Parcelable? = null

    private var gridLayoutManager: GridLayoutManager? = null
    private var recyclerView: RecyclerView? = null

    private val buttons = mutableListOf<TextView?>()
    private var adapter: WallpaperAdapter? = null

    private var searchView: SearchView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding?.let { binding ->
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(
                    systemBars.left, systemBars.top, systemBars.right,
                    systemBars.bottom
                )
                insets
            }
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSortingButtons()
        initRecyclerView()
        renderRecyclerState()
        connectivityStatus()
        searchWallpapers()
        changeSorting()
        initObservers()
        swipeToRefresh()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
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
    }

    fun initSortingButtons() {
        buttons.clear()
        buttons.addAll(
            listOf(
                binding?.hot,
                binding?.relevance,
                binding?.favorite,
                binding?.random,
                binding?.views
            )
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.buttonState.collectLatest { (oldValue, newValue) ->
                if (oldValue == newValue) return@collectLatest
                buttons[newValue]?.backgroundTintList =
                    ColorStateList.valueOf(
                        requireContext()
                            .getCustomColor(R.color.select_button_state)
                    )
                if (oldValue != -1) {
                    buttons[oldValue]?.backgroundTintList =
                        ColorStateList.valueOf(
                            requireContext()
                                .getCustomColor(R.color.default_button_state)
                        )
                }
            }
        }
    }

    fun initRecyclerView() {
        recyclerView = binding?.rvWallpapers
        gridLayoutManager = GridLayoutManager(requireContext(), 2)

        adapter = WallpaperAdapter(
            onItemClick = { item, view  ->
                launchDetailFragment(item, view)
            },
            onItemLongClick = { item ->
                launchBottomSheetDialog(item)
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

        recyclerView?.post {
            recyclerViewState?.let {
                recyclerView?.layoutManager?.onRestoreInstanceState(it)
            }
        }
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

    fun launchDetailFragment(id: Parcelable, clickedView: View) {
        val animation = NavOptions.Builder()
        animation.setEnterAnim(R.anim.zoom_enter)

        val location = IntArray(2)
        clickedView.getLocationOnScreen(location)

        val startX = location[0].toFloat() + clickedView.width / 2
        val startY = location[1].toFloat() + clickedView.height / 2

        // Pass position as arguments
        val args = DetailFragment.makeArgs(id).apply {
            putFloat("pivotX", startX)
            putFloat("pivotY", startY)
        }
        findNavController().navigate(
            R.id.detailFragment,
            args
        )
    }

    fun launchBottomSheetDialog(item: Parcelable){
        val args = BottomSheetFragment.makeArgs(item)
        val dialog = BottomSheetFragment().apply {
            this.arguments = args
        }
        dialog.show(this.parentFragmentManager, "entity")
    }

    private fun changeSorting() {
        buttons.forEachIndexed { index, button ->
            button?.setOnClickListener { view ->
                viewModel.sortWallpapers(index)
            }
        }
    }

    private fun searchWallpapers() {

        searchView?.clearFocus()
        searchView = binding?.searchQuery
        val clearButton = searchView?.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchWallpapers(query.toString().trim())
                binding?.rvWallpapers?.smoothScrollToPosition(0)
                viewModel.sortWallpapers(1)
                searchView?.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        clearButton?.setOnClickListener { v ->
            viewModel.searchWallpapers("")
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
                        ?.alpha(0f)
                        ?.setDuration(3000)
                        ?.withEndAction { binding?.connectionView?.visibility = View.GONE }
                        ?.start()
                    adapter?.refresh()
                } else {
                    binding?.connectionView?.animate()?.cancel()
                    binding?.connectionView?.clearAnimation()
                    binding?.connectionView?.alpha = 1f
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
            if (states.isIdle) {
                hideShimmer()
            } else {
                showShimmer()
            }
        }
    }

    private fun showShimmer() {
        binding?.shimmerLayout?.startShimmer()
    }

    private fun hideShimmer() {
        binding?.shimmerLayout?.apply {
            stopShimmer()
            visibility = View.GONE
        }
        binding?.swSortButtons?.visibility = View.VISIBLE
        binding?.rvWallpapers?.visibility = View.VISIBLE
        binding?.searchLayout?.visibility = View.VISIBLE
    }


    private fun swipeToRefresh() {
        val handler = Handler()
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
}