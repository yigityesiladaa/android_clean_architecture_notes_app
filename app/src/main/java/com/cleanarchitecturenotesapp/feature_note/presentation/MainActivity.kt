package com.cleanarchitecturenotesapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cleanarchitecturenotesapp.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.cleanarchitecturenotesapp.feature_note.presentation.favorite_notes.FavoriteNotesScreen
import com.cleanarchitecturenotesapp.feature_note.presentation.notes.NotesScreen
import com.cleanarchitecturenotesapp.feature_note.presentation.util.Screen
import com.cleanarchitecturenotesapp.ui.theme.CleanArchitectureNotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureNotesAppTheme(darkTheme = true) {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route,
                    ) {
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }

                        composable(route = Screen.FavoriteNotesScreen.route){
                            FavoriteNotesScreen(navController = navController)
                        }

                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(name = "noteId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(name = "noteColor") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val noteColor = it.arguments?.getInt("noteColor") ?: -1
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = noteColor,
                            )
                        }
                    }
                }
            }
        }
    }
}
