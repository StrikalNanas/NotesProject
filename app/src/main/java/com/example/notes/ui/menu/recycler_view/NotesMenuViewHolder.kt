package com.example.notes.ui.menu.recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.NoteDomain
import com.example.notes.databinding.ItemNoteBinding

class NotesMenuViewHolder(
    private val binding: ItemNoteBinding,
    private val onNoteClick: (NoteDomain) -> Unit,
    private val onDeleteClick: (NoteDomain) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: NoteDomain) {
        binding.title.text = note.title
        binding.content.text = note.content
        binding.root.setOnClickListener { onNoteClick(note) }
        binding.deleteButton.setOnClickListener { onDeleteClick(note) }
    }
}
