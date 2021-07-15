package com.example.chatbot.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordsDatabaseDao {
    @Insert
    suspend fun insert(word: Words)

    @Insert
    fun insertFirstWord(word: Words)

    @Update
    suspend fun update(word: Words)

    @Query("SELECT * FROM words_table WHERE wordId = :key")
    suspend fun get(key: Long): Words

    @Query("DELETE FROM words_table")
    suspend fun clear()

    @Query("SELECT * FROM words_table ORDER BY wordId DESC")
    fun getAllNights(): LiveData<List<Words>>

    @Query("SELECT * FROM words_table ORDER BY wordId DESC LIMIT 1")
    suspend fun getTonight(): Words?
}
