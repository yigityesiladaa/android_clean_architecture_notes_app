package com.cleanarchitecturenotesapp.feature_note.presentation.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cleanarchitecturenotesapp.feature_note.presentation.components.NoteList
import com.cleanarchitecturenotesapp.feature_note.presentation.components.SortFields
import com.cleanarchitecturenotesapp.feature_note.presentation.favorite_notes.FavoriteNotesEvent
import com.cleanarchitecturenotesapp.feature_note.presentation.notes.components.NoteItem
import com.cleanarchitecturenotesapp.feature_note.presentation.notes.components.OrderSection
import com.cleanarchitecturenotesapp.feature_note.presentation.util.Screen
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(
    navController: NavController, viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route)
                },
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Not Ekle")
            }
        },
    ) {
        it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Notlar", style = MaterialTheme.typography.headlineMedium
                )
                Row {
                    IconButton(onClick = { navController.navigate(Screen.FavoriteNotesScreen.route) }) {
                        Icon(
                            imageVector = Icons.Default.Favorite, tint = Color.White, contentDescription = "Favoriler"
                        )
                    }
                    IconButton(onClick = { viewModel.onEvent(NotesEvent.ToggleOrderSection) }) {
                        Icon(
                            imageVector = Icons.Default.Sort, contentDescription = "Sırala"
                        )
                    }
                }
            }
            SortFields(
                visible = state.isOrderSelectionVisible,
                noteOrderBy = state.noteOrderBy,
                onOrderChange = { noteOrderBy ->
                    viewModel.onEvent(NotesEvent.Order(noteOrderBy))
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            NoteList(
                notes = state.notes,
                navController = navController,
                onDeleteClick = { note ->
                    viewModel.onEvent(NotesEvent.DeleteNote(note))
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Not Silindi", actionLabel = "Geri Al", duration = SnackbarDuration.Long
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(NotesEvent.RestoreNote)
                        }
                    }
                },
                onAddDeleteFavoriteClick = { note ->
                    if (!note.isFavorite) {
                        viewModel.onEvent(NotesEvent.AddNoteToFavorites(noteModel = note))
                    } else {
                        viewModel.onEvent(NotesEvent.DeleteNoteFromFavorites(noteModel = note))
                    }
                },
            )
        }
    }
}