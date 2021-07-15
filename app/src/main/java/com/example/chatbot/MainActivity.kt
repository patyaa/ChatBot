package com.example.chatbot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chatbot.database.WordsDatabase
import com.example.chatbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = createViewModel()

        binding = setViewContent(this)
        binding.viewModel = viewModel
    }

    private fun createViewModel(): MainViewModel{
        val application = requireNotNull(this).application

        val dataSource = WordsDatabase.getInstance(application).wordsDatabaseDao

        val viewModelFactory = MainViewModelFactory(dataSource)

        return ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun setViewContent(activity: MainActivity): ActivityMainBinding {
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_main)
        binding.lifecycleOwner = activity
        return binding
    }

}