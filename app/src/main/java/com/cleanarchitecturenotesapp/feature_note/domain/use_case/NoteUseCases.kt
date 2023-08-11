package com.cleanarchitecturenotesapp.feature_note.domain.use_case

data class NoteUseCases(
    val getNotes: GetNoteUseCase,
    val getNoteById: GetNoteByIdUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNote: AddNoteUseCase,
)
