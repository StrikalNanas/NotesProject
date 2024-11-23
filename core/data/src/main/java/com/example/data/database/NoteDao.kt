package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.NoteData

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteData)

    @Query("DELETE FROM note WHERE id = :noteId")
    suspend fun delete(noteId: Int)

    @Query("SELECT * FROM note WHERE id = :noteId")
    suspend fun getNodeById(noteId: Int): NoteData?

    @Query("SELECT * FROM note")
    suspend fun getAllNote(): List<NoteData>
}