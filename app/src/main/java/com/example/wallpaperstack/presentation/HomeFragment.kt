package com.example.wallpaperstack.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperstack.R
import com.example.wallpaperstack.databinding.FragmentHomeBinding
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.presentation.adapters.WallpaperAdapter
import com.example.wallpaperstack.presentation.utils.MarginItemDecoration
import com.example.wallpaperstack.presentation.utils.getCustomColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.collections.listOf

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: WallpaperViewModel by viewModels()

    private var binding: FragmentHomeBinding? = null

    private var recyclerViewState: Parcelable? = null

    private var gridLayoutManager: GridLayoutManager? = null
    private var recyclerView: RecyclerView? = null

    private val buttons = mutableListOf<TextView?>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding?.let { binding ->
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSortingButtons()
        initRecyclerView()
        changeSorting()

    }

    fun initSortingButtons() {
        buttons.clear()
        buttons.addAll(
            listOf(
                binding?.topList,
                binding?.newest,
                binding?.favorite,
                binding?.random,
                binding?.views
            )
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.buttonState.collectLatest { (oldValue, newValue) ->
                if (oldValue == newValue) return@collectLatest
                buttons[newValue]?.backgroundTintList =
                    ColorStateList.valueOf(requireContext().getCustomColor(R.color.select_button_state))
                if (oldValue != -1) {
                    buttons[oldValue]?.backgroundTintList =
                        ColorStateList.valueOf(requireContext().getCustomColor(R.color.default_button_state))
                }
            }
        }
    }

    fun initRecyclerView() {
        recyclerView = binding?.rvWallpapers
        gridLayoutManager = GridLayoutManager(requireContext(), 2)

        val adapter = WallpaperAdapter(onItemClick = {
            launchDetailFragment(it)
        })

        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.adapter = adapter
        recyclerView?.addItemDecoration(
            MarginItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.margin_medium
                )
            )
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wallpapers.collectLatest {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }

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

    fun launchDetailFragment(id: Parcelable) {
        val animation = NavOptions.Builder()
        animation.setEnterAnim(R.anim.slide_left)
            .setExitAnim(R.anim.slide_right)
            .setPopEnterAnim(R.anim.slide_left)
            .setPopExitAnim(R.anim.slide_right)
        findNavController().navigate(
            R.id.detailFragment,
            DetailFragment.makeArgs(id),
            animation.build()
        )
    }

    fun changeSorting() {
        buttons.forEachIndexed { index, button ->
            button?.setOnClickListener { view ->
                viewModel.sortWallpapers(index)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
