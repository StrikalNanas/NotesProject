package com.example.notes.ui.menu

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
class NotesMenuViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _allNotes: MutableStateFlow<List<NoteDomain>> = MutableStateFlow(emptyList())
    val allNotes: StateFlow<List<NoteDomain>> = _allNotes

    fun updateAllNote() {
        viewModelScope.launch {
            _allNotes.value = noteRepository.getAllNote()
        }
    }

    fun deleteNoteById(noteId: Int) {
        viewModelScope.launch {
            noteRepository.deleteNoteById(noteId)
            updateAllNote()
        }
    }
}