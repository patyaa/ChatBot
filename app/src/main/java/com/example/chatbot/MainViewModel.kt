package com.example.chatbot

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel: ViewModel() {
    val words: ArrayList<String> = ArrayList()
    val chat = MutableLiveData<String>()
    val message = MutableLiveData<String>()
    init {
        words.add("hello")

    }

    fun onSend(){
        Log.d("MainViewModel", "input text = ${message.value}")
        val msg = message.value
        if(!msg.isNullOrEmpty()) {
            //message kiírása chatre
            chat.value = chat.value + "\n" + message.value
            //válasz kiíársa
            val response = words[Random.nextInt(words.size)]
            chat.value = chat.value + "\n" + response
            //words frissítése
            words.add(msg)
        }
    }



}