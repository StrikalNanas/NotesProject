package com.example.notes.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.databinding.FragmentNotesMenuBinding
import com.example.notes.ui.menu.recycler_view.NotesMenuAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesMenuFragment : Fragment() {

    private lateinit var viewBinding: FragmentNotesMenuBinding

    private val viewModel: NotesMenuViewModel by viewModels()

    private lateinit var notesMenuAdapter: NotesMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNotesMenuBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesMenuAdapter = NotesMenuAdapter(
            emptyList(),
            onNoteClick = { note ->
                val bundle = Bundle().apply {
                    putInt("noteId", note.id ?: -1)
                }
                findNavController().navigate(R.id.action_note_menu_fragment_to_note_editor_fragment, bundle)
            },

            onDeleteClick = { note ->
                viewModel.deleteNoteById(note.id!!)
            }
        )

        viewBinding.noteRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notesMenuAdapter
        }

        lifecycleScope.launch {
            viewModel.allNotes.collect { notes ->
                notesMenuAdapter.updateNotes(notes)
            }
        }

        viewBinding.addNoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_note_menu_fragment_to_note_editor_fragment)
        }

        viewModel.updateAllNote()
    }
}