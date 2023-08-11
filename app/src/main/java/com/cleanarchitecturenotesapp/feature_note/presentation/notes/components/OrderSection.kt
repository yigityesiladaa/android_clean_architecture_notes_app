package com.cleanarchitecturenotesapp.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cleanarchitecturenotesapp.feature_note.domain.util.NoteOrderBy
import com.cleanarchitecturenotesapp.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrderBy: NoteOrderBy = NoteOrderBy.Date(OrderType.Descending),
    onOrderChange: (NoteOrderBy) -> Unit,
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Başlık", selected = noteOrderBy is NoteOrderBy.Title, onSelect = {
                onOrderChange(NoteOrderBy.Title(noteOrderBy.orderType))
            })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Tarih", selected = noteOrderBy is NoteOrderBy.Date, onSelect = {
                onOrderChange(NoteOrderBy.Date(noteOrderBy.orderType))
            })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Renk", selected = noteOrderBy is NoteOrderBy.Color, onSelect = {
                onOrderChange(NoteOrderBy.Color(noteOrderBy.orderType))
            })

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Normal Sırala", selected = noteOrderBy.orderType is OrderType.Descending, onSelect = {
                onOrderChange(noteOrderBy.copy(OrderType.Descending))
            })

            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Tersten Sırala", selected = noteOrderBy.orderType is OrderType.Ascending, onSelect = {
                onOrderChange(noteOrderBy.copy(OrderType.Ascending))
            })
        }
    }
}