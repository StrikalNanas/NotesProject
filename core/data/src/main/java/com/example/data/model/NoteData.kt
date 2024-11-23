package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class NoteData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val content: String
)