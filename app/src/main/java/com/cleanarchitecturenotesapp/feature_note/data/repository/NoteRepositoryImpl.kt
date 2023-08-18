package com.cleanarchitecturenotesapp.feature_note.data.repository

import com.cleanarchitecturenotesapp.feature_note.data.data_source.NoteDao
import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
): INoteRepository {
    override fun getNotes(): Flow<List<NoteModel>>{
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): NoteModel?{
        return dao.getNoteById(id)
    }

    override fun getNotesByIdList(noteIds: List<Int>): Flow<List<NoteModel>>{
        return dao.getNotesByIdList(noteIds = noteIds)
    }

    override suspend fun insertNote(noteModel: NoteModel) = dao.insertNote(noteModel)

    override suspend fun deleteNote(noteModel: NoteModel) = dao.deleteNote(noteModel)
}