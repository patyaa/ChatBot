package com.example.chatbot.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface WordsDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(string: Words)

    @Query("SELECT * FROM words_table ORDER BY RANDOM() LIMIT 1")
    suspend fun getResponse(): Words

    @Query("DELETE FROM words_table")
    suspend fun clear()
}
