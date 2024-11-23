package com.example.data.di

import com.example.data.database.NoteDatabase
import com.example.data.repository.NoteRepositoryImpl
import com.example.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NoteRepositoryModule {

    @Singleton
    @Provides
    fun provideNoteRepository(
        noteDatabase: NoteDatabase
    ): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao())
    }
}