package com.cleanarchitecturenotesapp.feature_note.domain.use_case

import com.cleanarchitecturenotesapp.feature_note.domain.model.FavoriteNoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.repository.IFavoriteNoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteNotesUseCase(
    private val repository: IFavoriteNoteRepository
) {
    operator fun invoke(): Flow<List<FavoriteNoteModel>> {
        return repository.getFavoriteNotes().map { notes ->
            notes.sortedBy { it.timeStamp }
        }
    }
}