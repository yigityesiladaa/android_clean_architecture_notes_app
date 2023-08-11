package com.cleanarchitecturenotesapp.feature_note.domain.repository

import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface INoteRepository {

    fun getNotes(): Flow<List<NoteModel>>

    suspend fun getNoteById(id: Int): NoteModel?

    suspend fun insertNote(noteModel: NoteModel)

    suspend fun deleteNote(noteModel: NoteModel)
}