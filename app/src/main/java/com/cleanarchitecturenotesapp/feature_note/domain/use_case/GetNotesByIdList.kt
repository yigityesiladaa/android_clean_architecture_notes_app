package com.cleanarchitecturenotesapp.feature_note.domain.use_case

import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.repository.INoteRepository
import com.cleanarchitecturenotesapp.feature_note.domain.util.NoteOrderBy
import com.cleanarchitecturenotesapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesByIdList(
    private val repository: INoteRepository
) {
    operator fun invoke(noteIds: List<Int>,noteOrderBy: NoteOrderBy = NoteOrderBy.Date(OrderType.Descending)): Flow<List<NoteModel>> {
        return repository.getNotesByIdList(noteIds).map { notes ->
            notes.sortedWith(
                when (noteOrderBy.orderType) {
                    is OrderType.Ascending -> compareBy {
                        when (noteOrderBy) {
                            is NoteOrderBy.Title -> it.title.lowercase()
                            is NoteOrderBy.Date -> it.timeStamp
                            is NoteOrderBy.Color -> it.color
                        }
                    }
                    is OrderType.Descending -> compareByDescending {
                        when (noteOrderBy) {
                            is NoteOrderBy.Title -> it.title.lowercase()
                            is NoteOrderBy.Date -> it.timeStamp
                            is NoteOrderBy.Color -> it.color
                        }
                    }
                }
            )
        }
    }
}