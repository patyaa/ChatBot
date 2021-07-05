package com.example.chatbot

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.chatbot.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var words: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setViewContent(this)

        words.add("hello")

        binding.sendButton.setOnClickListener {
            addText(it)
            response()
        }
    }

    private fun setViewContent(activity: MainActivity): ActivityMainBinding {
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        return binding
    }

    private fun addText(view: View) {
        val chat = binding.chatText
        val fromInput = binding.message

        chat.text = fromInput.text
        chat.visibility = View.VISIBLE
        Log.d("MainActivity", chat.text.toString())

        //szöveg hozzáadása a listához
        words.add(chat.text.toString())
        Log.d("MainActivity", words.toString())
    }

    private fun response() {
        val botAnswer = binding.chatText

        val wordIndex =
            Random().nextInt(words.size - 1) //az aktuálisan bevitt szöveg már ne legyen benne
        Log.d("MainActivity", wordIndex.toString())
        botAnswer.text = words[wordIndex]
    }
}