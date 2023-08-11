package com.cleanarchitecturenotesapp.di

import android.app.Application
import androidx.room.Room
import com.cleanarchitecturenotesapp.feature_note.data.data_source.NoteDatabase
import com.cleanarchitecturenotesapp.feature_note.data.repository.NoteRepositoryImpl
import com.cleanarchitecturenotesapp.feature_note.domain.repository.INoteRepository
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.AddNoteUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.GetNoteByIdUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.GetNoteUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): INoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: INoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNoteUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNoteById = GetNoteByIdUseCase(repository)
        )
    }
}