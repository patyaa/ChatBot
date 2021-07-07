package com.example.chatbot

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chatbot.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = setViewContent(this)
        binding.viewModel = viewModel
    }

    private fun setViewContent(activity: MainActivity): ActivityMainBinding {
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(activity, R.layout.activity_main)
        binding.lifecycleOwner = activity
        return binding
    }
}