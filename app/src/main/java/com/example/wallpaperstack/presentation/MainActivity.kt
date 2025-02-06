package com.example.wallpaperstack.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaperstack.R
import com.example.wallpaperstack.base.ViewModelFactory
import com.example.wallpaperstack.data.repository.WallpapersRepository
import com.example.wallpaperstack.databinding.ActivityMainBinding
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.presentation.adapters.WallpaperAdapter
import com.example.wallpaperstack.presentation.utils.MarginItemDecoration
import com.example.wallpaperstack.presentation.utils.getCustomColor
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: WallpapersRepository

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: WallpaperViewModel

    private lateinit var binding: ActivityMainBinding

    private var pressedStateButton: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSystemBar()
        changeSorting()

        pressedStateButton = binding.newest
        pressedStateButton?.backgroundTintList =
            ColorStateList.valueOf(getCustomColor(R.color.select_button_state))

        viewModel = ViewModelProvider(this, viewModelFactory)[WallpaperViewModel::class.java]
        val adapter = WallpaperAdapter()
        binding.rvWallpapers.adapter = adapter

        viewModel.wallpapers.observe(this) { wallpapersPage ->
            adapter.submitData(this.lifecycle, wallpapersPage)
        }

        val recyclerView = binding.rvWallpapers
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_medium)))
    }

    fun changeSorting() {
        val buttons = listOf(
            binding.topList,
            binding.newest,
            binding.favorite,
            binding.random,
            binding.views
        )
        val value = listOf(
            Sorting.TOP_LIST, Sorting.DATE_ADDED, Sorting.FAVORITES, Sorting.RANDOM, Sorting.VIEWS
        )
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener { v ->
                if (pressedStateButton == button) return@setOnClickListener
                button.backgroundTintList =
                    ColorStateList.valueOf(getCustomColor(R.color.select_button_state))
                pressedStateButton?.backgroundTintList =
                    ColorStateList.valueOf(getCustomColor(R.color.default_button_state))
                pressedStateButton = button
                    viewModel.searchWallpapers(value[index])
            }
        }
    }

        fun initSystemBar() {
            enableEdgeToEdge()
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
        }
    }
}

