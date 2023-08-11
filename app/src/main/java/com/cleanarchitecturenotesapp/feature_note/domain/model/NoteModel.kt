package com.cleanarchitecturenotesapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cleanarchitecturenotesapp.ui.theme.BabyBlue
import com.cleanarchitecturenotesapp.ui.theme.LightGreen
import com.cleanarchitecturenotesapp.ui.theme.RedOrange
import com.cleanarchitecturenotesapp.ui.theme.RedPink
import com.cleanarchitecturenotesapp.ui.theme.Violet

@Entity(tableName = "note")
data class NoteModel(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)