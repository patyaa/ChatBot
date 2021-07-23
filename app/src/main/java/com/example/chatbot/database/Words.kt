package com.example.chatbot.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words_table")
class Words(
    @PrimaryKey
    var words: String
)