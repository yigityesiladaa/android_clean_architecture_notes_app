package com.cleanarchitecturenotesapp.feature_note.domain.use_case

import com.cleanarchitecturenotesapp.feature_note.domain.repository.IFavoriteNoteRepository

class DeleteNoteFromFavoritesUseCase (
    private val repository: IFavoriteNoteRepository
) {
    suspend operator fun invoke(noteId: Int){
        repository.deleteNoteFromFavorites(noteId = noteId)
    }
}