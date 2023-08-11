package com.cleanarchitecturenotesapp.feature_note.domain.use_case

import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.repository.INoteRepository

class GetNoteByIdUseCase(
    private val repository: INoteRepository
) {
    suspend operator fun invoke(id: Int): NoteModel?{
        return repository.getNoteById(id)
    }
}