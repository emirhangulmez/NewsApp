package com.emirhangulmez.newsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emirhangulmez.newsapp.common.viewBinding
import com.emirhangulmez.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}