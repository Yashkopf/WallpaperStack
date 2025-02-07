package com.example.wallpaperstack.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaperstack.R
import com.example.wallpaperstack.base.ViewModelFactory
import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.databinding.FragmentHomeBinding
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.presentation.adapters.WallpaperAdapter
import com.example.wallpaperstack.presentation.utils.MarginItemDecoration
import com.example.wallpaperstack.presentation.utils.getCustomColor
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var repository: WallpapersRepository

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: WallpaperViewModel

    private var binding: FragmentHomeBinding? = null

    private var pressedStateButton: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)

        changeSorting()

        pressedStateButton = binding?.newest
        pressedStateButton?.backgroundTintList =
            ColorStateList.valueOf(requireContext().getCustomColor(R.color.select_button_state))

        viewModel = ViewModelProvider(this, viewModelFactory)[WallpaperViewModel::class.java]
        val adapter = WallpaperAdapter()

        binding?.rvWallpapers?.adapter = adapter
        binding?.rvWallpapers?.layoutManager = GridLayoutManager(requireContext(), 2)
        binding?.rvWallpapers?.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_medium)))

        viewModel.wallpapers.observe(viewLifecycleOwner) { wallpapersPage ->
            adapter.submitData(viewLifecycleOwner.lifecycle, wallpapersPage)
        }
    }

    fun changeSorting() {
        val buttons = listOf(
            binding?.topList,
            binding?.newest,
            binding?.favorite,
            binding?.random,
            binding?.views
        )
        val value = listOf(
            Sorting.TOP_LIST, Sorting.DATE_ADDED, Sorting.FAVORITES, Sorting.RANDOM, Sorting.VIEWS
        )
        buttons.forEachIndexed { index, button ->
            button?.setOnClickListener { v ->
                if (pressedStateButton == button) return@setOnClickListener
                button.backgroundTintList =
                    ColorStateList.valueOf(requireContext().getCustomColor(R.color.select_button_state))
                pressedStateButton?.backgroundTintList =
                    ColorStateList.valueOf(requireContext().getCustomColor(R.color.default_button_state))
                pressedStateButton = button
                viewModel.searchWallpapers(value[index])
            }
        }
    }
}
