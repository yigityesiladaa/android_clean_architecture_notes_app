package com.cleanarchitecturenotesapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.NoteUseCases
import com.cleanarchitecturenotesapp.feature_note.domain.util.NoteOrderBy
import com.cleanarchitecturenotesapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNoteModel: NoteModel? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrderBy.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrderBy == event.noteOrderBy && state.value.noteOrderBy.orderType == event.noteOrderBy.orderType) {
                    return
                }
                getNotes(event.noteOrderBy)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.noteModel)
                    recentlyDeletedNoteModel = event.noteModel
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNoteModel ?: return@launch)
                    recentlyDeletedNoteModel = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSelectionVisible = !state.value.isOrderSelectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrderBy: NoteOrderBy) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrderBy).onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrderBy = noteOrderBy,
                )
            }.launchIn(viewModelScope)
    }

}