package com.example.chatbot.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words_table")
class Words(
    @PrimaryKey(autoGenerate = true)
    var wordId: Long = 0L,

    @ColumnInfo(name = "words")
    var words: String
)
