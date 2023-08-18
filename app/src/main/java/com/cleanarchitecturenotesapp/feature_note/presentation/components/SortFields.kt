package com.cleanarchitecturenotesapp.feature_note.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cleanarchitecturenotesapp.feature_note.domain.util.NoteOrderBy
import com.cleanarchitecturenotesapp.feature_note.presentation.notes.NotesEvent
import com.cleanarchitecturenotesapp.feature_note.presentation.notes.components.OrderSection

@Composable
fun SortFields(
    visible: Boolean,
    noteOrderBy: NoteOrderBy,
    onOrderChange: (NoteOrderBy) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically(),
    ) {
        OrderSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            noteOrderBy = noteOrderBy,
            onOrderChange = onOrderChange
        )
    }
}