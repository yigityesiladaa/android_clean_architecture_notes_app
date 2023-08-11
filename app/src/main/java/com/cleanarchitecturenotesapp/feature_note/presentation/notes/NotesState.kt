package com.cleanarchitecturenotesapp.feature_note.presentation.notes

import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.util.NoteOrderBy
import com.cleanarchitecturenotesapp.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<NoteModel> = emptyList(),
    val noteOrderBy: NoteOrderBy = NoteOrderBy.Date(OrderType.Descending),
    val isOrderSelectionVisible: Boolean = false,
)
