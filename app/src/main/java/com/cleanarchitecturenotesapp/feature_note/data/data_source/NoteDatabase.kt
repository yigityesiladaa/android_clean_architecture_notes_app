package com.cleanarchitecturenotesapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cleanarchitecturenotesapp.feature_note.domain.model.FavoriteNoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel

@Database(entities = [NoteModel::class, FavoriteNoteModel::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val favoriteNoteDao: FavoriteNoteDao

    companion object{
        const val DATABASE_NAME = "notes_database"
    }
}