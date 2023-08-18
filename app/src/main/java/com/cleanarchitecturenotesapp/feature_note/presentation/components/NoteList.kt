package com.cleanarchitecturenotesapp.feature_note.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import com.cleanarchitecturenotesapp.feature_note.presentation.notes.components.NoteItem
import com.cleanarchitecturenotesapp.feature_note.presentation.util.Screen

@Composable
fun NoteList(
    notes: List<NoteModel>,
    navController: NavController,
    onDeleteClick: (NoteModel) -> Unit,
    onAddDeleteFavoriteClick: (NoteModel) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(notes) { note ->
            NoteItem(
                note = note,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(
                            Screen.AddEditNoteScreen.route + "?noteId=${note.id}&noteColor=${note.color}"
                        )
                    },
                onDeleteClick = {
                    onDeleteClick(note)
                },
                onAddDeleteFavoriteClick = {
                    onAddDeleteFavoriteClick(note)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}