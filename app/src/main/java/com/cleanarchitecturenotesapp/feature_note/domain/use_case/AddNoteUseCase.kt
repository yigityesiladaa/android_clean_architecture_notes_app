package com.cleanarchitecturenotesapp.feature_note.domain.use_case

import com.cleanarchitecturenotesapp.feature_note.domain.model.InvalidNoteException
import com.cleanarchitecturenotesapp.feature_note.domain.model.NoteModel
import com.cleanarchitecturenotesapp.feature_note.domain.repository.INoteRepository
import kotlin.jvm.Throws

class AddNoteUseCase(
    private val repository: INoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(noteModel: NoteModel){
        when{
            noteModel.title.isBlank() -> throw InvalidNoteException("Başlık boş olamaz!")
            noteModel.content.isBlank() -> throw InvalidNoteException("İçerik boş olamaz!")
        }
        repository.insertNote(noteModel)
    }
}