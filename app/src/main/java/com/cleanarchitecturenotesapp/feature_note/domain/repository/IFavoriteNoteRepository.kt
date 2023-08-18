package com.cleanarchitecturenotesapp.feature_note.domain.repository

import com.cleanarchitecturenotesapp.feature_note.domain.model.FavoriteNoteModel
import kotlinx.coroutines.flow.Flow

interface IFavoriteNoteRepository {

    fun getFavoriteNotes(): Flow<List<FavoriteNoteModel>>
    suspend fun insertNoteToFavorites(favoriteNoteModel: FavoriteNoteModel)
    suspend fun deleteNoteFromFavorites(noteId: Int)
}