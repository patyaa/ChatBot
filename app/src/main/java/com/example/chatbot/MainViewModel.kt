package com.example.chatbot

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.database.Words
import com.example.chatbot.database.WordsDatabaseDao
import com.example.chatbot.model.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val database: WordsDatabaseDao) : ViewModel() {
    val message = MutableLiveData<String>()

    val messages = MutableLiveData<List<Message>>()

    private var _showSnackBarEvent = MutableLiveData<Boolean>()
    val showSnackbarEvent: LiveData<Boolean>
        get() = _showSnackBarEvent

    fun doneShowingSnackbar() {
        _showSnackBarEvent.value = false
    }

    fun onSend() {
        message.value?.let { msg ->
            viewModelScope.launch {
                //User válaszát elmenteni adatbázisba
                insert(Words(msg))

                //Keressen rá választ
                val response = getResponse()

                //Kérdés és válasz kiírása
                updateChat(msg, response.words)
            }
        }
    }

    private fun updateChat(question: String, answer: String) {
        messages.value = messages.value.orEmpty().toMutableList().apply {
            add(Message(Color.GREEN, question))
            add(Message(Color.GRAY, answer))
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
            _showSnackBarEvent.value = true
        }
    }

    private suspend fun insert(word: Words) {
        withContext(Dispatchers.IO) {
            database.insert(word)
        }
    }

    private suspend fun getResponse(): Words {
        return withContext(Dispatchers.IO) {
            database.getResponse()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }
}
