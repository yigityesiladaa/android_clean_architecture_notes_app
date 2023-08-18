package com.cleanarchitecturenotesapp.feature_note.presentation.notes

import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.util.NoteOrderBy
import com.cleanarchitecturenotesapp.feature_note.presentation.favorite_notes.FavoriteNotesEvent

sealed class NotesEvent{
    data class Order(val noteOrderBy: NoteOrderBy): NotesEvent()
    data class DeleteNote(val noteModel: NoteModel): NotesEvent()
    data class AddNoteToFavorites(val noteModel: NoteModel): NotesEvent()
    data class DeleteNoteFromFavorites(val noteModel: NoteModel): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
