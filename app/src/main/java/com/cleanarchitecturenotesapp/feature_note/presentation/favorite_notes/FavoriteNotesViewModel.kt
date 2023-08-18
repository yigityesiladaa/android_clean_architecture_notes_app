package com.cleanarchitecturenotesapp.feature_note.presentation.favorite_notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.NoteUseCases
import com.cleanarchitecturenotesapp.feature_note.domain.util.NoteOrderBy
import com.cleanarchitecturenotesapp.feature_note.domain.util.OrderType
import com.cleanarchitecturenotesapp.feature_note.presentation.notes.NotesEvent
import com.cleanarchitecturenotesapp.feature_note.presentation.notes.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteNotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var getFavoriteNotesJob: Job? = null

    init {
        getFavoriteNotes(NoteOrderBy.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrderBy == event.noteOrderBy && state.value.noteOrderBy.orderType == event.noteOrderBy.orderType) {
                    return
                }
                getFavoriteNotes(event.noteOrderBy)
            }

            is NotesEvent.AddNoteToFavorites -> {
                viewModelScope.launch {
                    event.noteModel.id?.let { noteId ->
                        noteUseCases.addNoteToFavorites(noteId = noteId)
                    }
                    val updatedNote = event.noteModel.copy(isFavorite = true)
                    noteUseCases.addNote(updatedNote)
                }
            }

            is NotesEvent.DeleteNoteFromFavorites -> {
                viewModelScope.launch {
                    event.noteModel.id?.let { noteId ->
                        noteUseCases.deleteNoteFromFavorites(noteId = noteId)
                    }
                    val updatedNote = event.noteModel.copy(isFavorite = false)
                    noteUseCases.addNote(updatedNote)
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSelectionVisible = !state.value.isOrderSelectionVisible
                )
            }

            else -> {}
        }
    }

    private fun getFavoriteNotes(noteOrderBy: NoteOrderBy) {
        getFavoriteNotesJob?.cancel()
        getFavoriteNotesJob = noteUseCases.getFavoriteNotesUseCase().onEach { favoriteNotes ->
            val favoriteNoteIds = favoriteNotes.map { it.noteId }

            viewModelScope.launch {
                val notes = mutableListOf<NoteModel>()
                noteUseCases.getNotesByIdList(noteIds = favoriteNoteIds, noteOrderBy = noteOrderBy)
                    .collect { noteList ->
                        notes.clear()
                        notes.addAll(noteList)
                        _state.value = state.value.copy(
                            notes = notes,
                            noteOrderBy = noteOrderBy,
                        )
                    }
            }
        }.launchIn(viewModelScope)
    }

}