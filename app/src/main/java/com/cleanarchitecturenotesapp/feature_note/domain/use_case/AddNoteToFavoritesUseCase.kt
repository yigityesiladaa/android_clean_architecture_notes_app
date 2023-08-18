package com.cleanarchitecturenotesapp.feature_note.domain.use_case

import com.cleanarchitecturenotesapp.feature_note.domain.model.FavoriteNoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.repository.IFavoriteNoteRepository

class AddNoteToFavoritesUseCase(
    private val repository: IFavoriteNoteRepository
) {
    suspend operator fun invoke(noteId: Int){
        val favoriteNoteModel = FavoriteNoteModel(
            id = null,
            noteId = noteId,
            timeStamp = System.currentTimeMillis(),
        )
        repository.insertNoteToFavorites(favoriteNoteModel = favoriteNoteModel)
    }
}