package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.NoteData

@Database(
    entities = [NoteData::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "note_database"
    }
}