package com.example.wallpaperstack.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wallpaperstack.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSystemBar()
        setContentView(R.layout.activity_main)
    }

    fun initSystemBar() {
        enableEdgeToEdge()
    }
}


