package com.example.notes.ui.menu.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.NoteDomain
import com.example.notes.databinding.ItemNoteBinding

class NotesMenuAdapter(
    private var noteList: List<NoteDomain>,
    private val onNoteClick: (NoteDomain) -> Unit,
    private val onDeleteClick: (NoteDomain) -> Unit
) : RecyclerView.Adapter<NotesMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesMenuViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesMenuViewHolder(binding, onNoteClick, onDeleteClick)
    }

    override fun onBindViewHolder(holder: NotesMenuViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount(): Int = noteList.size

    fun updateNotes(newNotes: List<NoteDomain>) {
        noteList = newNotes
        notifyDataSetChanged()
    }
}
