package com.cleanarchitecturenotesapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteNoteModel(
    @PrimaryKey val id: Int? = null,
    val noteId: Int,
    val timeStamp: Long,
)