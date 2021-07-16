package com.example.chatbot

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.database.Words
import com.example.chatbot.database.WordsDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val database: WordsDatabaseDao) : ViewModel() {
    val chat = MutableLiveData<String>()
    val message = MutableLiveData<String>()


    fun onSend() {
        message.value?.let { msg ->
            viewModelScope.launch {
                //User válaszát elmenteni adatbázisba
                insert(Words(msg))

                //Kereseen rá választ
                val response = getResponse()

                //Válasz kiírása
                chat.value = chat.value + "\n" + message.value + "\n" + response.words + "\n"
            }
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
}
