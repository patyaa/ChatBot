package com.example.chatbot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatbot.database.WordsDatabase
import com.example.chatbot.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = createViewModel()

        binding = setViewContent(this)
        binding.viewModel = viewModel

        viewModel.showSnackbarEvent.observe(this, Observer {
            if(it == true){
                Snackbar.make(
                    this.findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackbar()
            }
        })

        val adapter = WordsAdapter()
        binding.wordsList.adapter = adapter
        viewModel.messages.observe(this, Observer {
            it?.let {
                adapter.data = it
            }
        })
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