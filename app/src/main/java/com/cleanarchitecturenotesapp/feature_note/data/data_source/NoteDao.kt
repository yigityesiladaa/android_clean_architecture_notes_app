package com.cleanarchitecturenotesapp.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<NoteModel>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteModel: NoteModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteModel)
}