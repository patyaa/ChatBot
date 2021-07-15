package com.example.chatbot

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.database.Words
import com.example.chatbot.database.WordsDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainViewModel(val database: WordsDatabaseDao) : ViewModel() {
    private val viewModelJob = Job()
    private val allWords = database.getAllWords()
    private var word = MutableLiveData<Words>()

    val words: ArrayList<String> = ArrayList()
    val chat = MutableLiveData<String>()
    val message = MutableLiveData<String>()


    init {
        Log.d("MainViewModel", "msg: $message")
        //words.add("hello")
        Words(0, "hello")
        initializeFirstWord()
    }

    private fun initializeFirstWord() {
        viewModelScope.launch {
            word.value = getCurrentWordFromDatabase()
            Log.d("MainViewModel", "word: $word")
        }
    }

    fun onSend() {

        viewModelScope.launch {
            Log.d("MainViewModel", "input text = ${message.value}")
            val msg = Words(1, message.value.toString())
            insert(msg)

            //message kiírása chatre
            chat.value = chat.value + "\n" + message.value
            //válasz kiíársa
            val response = words[Random.nextInt(words.size)]
            chat.value = chat.value + "\n" + response
            //words frissítése
            //words.add(msg)
        }
    }

    private fun insert(word: Words) {

        database.insert(word)

    }

    private suspend fun getCurrentWordFromDatabase(): Words? {
        return withContext(Dispatchers.IO) {
            val current = database.getCurrentWord()
            current
        }
    }

    private suspend fun update(night: Words) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}