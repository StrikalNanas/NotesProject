package com.example.domain.repository

import com.example.domain.model.NoteDomain

interface NoteRepository {

    suspend fun addNote(note: NoteDomain)

    suspend fun deleteNoteById(noteId: Int)

    suspend fun getNoteById(noteId: Int): NoteDomain?

    suspend fun getAllNote(): List<NoteDomain>
}