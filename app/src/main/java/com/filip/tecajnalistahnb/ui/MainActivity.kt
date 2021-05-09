package com.filip.tecajnalistahnb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.filip.tecajnalistahnb.R

class MainActivity : AppCompatActivity() {

    private val moviesViewModel by viewModel<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}