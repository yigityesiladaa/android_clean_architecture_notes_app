package com.cleanarchitecturenotesapp.feature_note.presentation.favorite_notes

import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel

sealed class FavoriteNotesEvent {
    data class AddNoteToFavorites(val noteModel: NoteModel): FavoriteNotesEvent()
    data class DeleteNoteFromFavorites(val noteModel: NoteModel): FavoriteNotesEvent()
}