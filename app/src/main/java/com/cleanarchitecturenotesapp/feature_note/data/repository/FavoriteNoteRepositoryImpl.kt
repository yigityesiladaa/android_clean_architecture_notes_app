package com.cleanarchitecturenotesapp.feature_note.data.repository

import com.cleanarchitecturenotesapp.feature_note.data.data_source.FavoriteNoteDao
import com.cleanarchitecturenotesapp.feature_note.domain.model.FavoriteNoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.repository.IFavoriteNoteRepository
import kotlinx.coroutines.flow.Flow

class FavoriteNoteRepositoryImpl(
    private val dao: FavoriteNoteDao
) : IFavoriteNoteRepository {
    override fun getFavoriteNotes(): Flow<List<FavoriteNoteModel>> {
        return dao.getFavoriteNotes()
    }

    override suspend fun insertNoteToFavorites(favoriteNoteModel: FavoriteNoteModel) =
        dao.insertNoteToFavorites(favoriteNoteModel)

    override suspend fun deleteNoteFromFavorites(noteId: Int) =
        dao.deleteNoteFromFavorites(noteId)

}