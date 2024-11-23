package com.example.notes.ui.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.model.NoteDomain
import com.example.notes.databinding.FragmentNoteEditorBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteEditorFragment : Fragment() {

    private lateinit var viewBinding: FragmentNoteEditorBinding
    private val viewModel: NoteEditorViewModel by viewModels()

    private val noteId: Int? by lazy {
        arguments?.getInt("noteId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNoteEditorBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {
            saveNoteButton.setOnClickListener {
                val title = editorTitle.text.toString()
                val content = editorContent.text.toString()

                if (title.isNotBlank() && content.isNotBlank()) {
                    val note = NoteDomain(
                        id = noteId,
                        title = title,
                        content = content
                    )

                    viewModel.addNote(note)
                    findNavController().popBackStack()
                }
            }
        }

        noteId?.let {
            viewModel.getNoteById(it)
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.itemNote.collect { note ->
                    note?.let {
                        viewBinding.apply {
                            editorTitle.setText(it.title)
                            editorContent.setText(it.content)
                        }
                    }
                }
            }
        }
    }
}