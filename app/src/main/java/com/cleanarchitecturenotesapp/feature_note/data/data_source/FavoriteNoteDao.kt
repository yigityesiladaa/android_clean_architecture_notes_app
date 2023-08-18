package com.cleanarchitecturenotesapp.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cleanarchitecturenotesapp.feature_note.domain.model.FavoriteNoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteNoteDao {
    @Query("SELECT * FROM favorite")
    fun getFavoriteNotes(): Flow<List<FavoriteNoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteToFavorites(favoriteNoteModel: FavoriteNoteModel)

    @Query("DELETE FROM favorite WHERE noteId = :noteId")
    suspend fun deleteNoteFromFavorites(noteId: Int)
}