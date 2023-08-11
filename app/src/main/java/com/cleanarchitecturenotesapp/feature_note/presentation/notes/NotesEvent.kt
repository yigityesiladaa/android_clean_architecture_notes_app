package com.cleanarchitecturenotesapp.feature_note.presentation.notes

import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.util.NoteOrderBy

sealed class NotesEvent{
    data class Order(val noteOrderBy: NoteOrderBy): NotesEvent()
    data class DeleteNote(val noteModel: NoteModel): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
