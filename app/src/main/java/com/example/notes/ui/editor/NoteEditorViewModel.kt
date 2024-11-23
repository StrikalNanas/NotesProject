package com.example.notes.ui.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.NoteDomain
import com.example.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteEditorViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _itemNote: MutableStateFlow<NoteDomain?> = MutableStateFlow(null)
    val itemNote: StateFlow<NoteDomain?> = _itemNote

    fun getNoteById(noteId: Int) {
        viewModelScope.launch {
            _itemNote.value = noteRepository.getNoteById(noteId)
        }
    }

    fun addNote(note: NoteDomain) {
        viewModelScope.launch {
            noteRepository.addNote(note)
        }
    }
}