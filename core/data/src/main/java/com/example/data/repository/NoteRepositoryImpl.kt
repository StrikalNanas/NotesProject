package com.example.data.repository

import com.example.data.database.NoteDao
import com.example.data.model.toData
import com.example.data.model.toDomain
import com.example.domain.model.NoteDomain
import com.example.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override suspend fun addNote(note: NoteDomain) {
        noteDao.insertNote(note.toData())
    }

    override suspend fun deleteNoteById(noteId: Int) {
        noteDao.delete(noteId)
    }

    override suspend fun getNoteById(noteId: Int): NoteDomain? {
        return noteDao.getNodeById(noteId)?.toDomain()
    }

    override suspend fun getAllNote(): List<NoteDomain> {
        return noteDao.getAllNote().map { it.toDomain() }
    }
}