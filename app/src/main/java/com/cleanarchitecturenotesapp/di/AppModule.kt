package com.cleanarchitecturenotesapp.di

import android.app.Application
import androidx.room.Room
import com.cleanarchitecturenotesapp.feature_note.data.data_source.NoteDatabase
import com.cleanarchitecturenotesapp.feature_note.data.repository.FavoriteNoteRepositoryImpl
import com.cleanarchitecturenotesapp.feature_note.data.repository.NoteRepositoryImpl
import com.cleanarchitecturenotesapp.feature_note.domain.repository.IFavoriteNoteRepository
import com.cleanarchitecturenotesapp.feature_note.domain.repository.INoteRepository
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.AddNoteToFavoritesUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.AddNoteUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.DeleteNoteFromFavoritesUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.GetFavoriteNotesUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.GetNoteByIdUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.GetNoteUseCase
import com.cleanarchitecturenotesapp.feature_note.domain.use_case.GetNotesByIdList
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
    fun provideFavoriteNoteRepository(db: NoteDatabase): IFavoriteNoteRepository {
        return FavoriteNoteRepositoryImpl(db.favoriteNoteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(
        noteRepository: INoteRepository,
        favoriteNoteRepository: IFavoriteNoteRepository
    ): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNoteUseCase(noteRepository),
            deleteNote = DeleteNoteUseCase(noteRepository),
            addNote = AddNoteUseCase(noteRepository),
            getNoteById = GetNoteByIdUseCase(noteRepository),
            getNotesByIdList = GetNotesByIdList(noteRepository),
            addNoteToFavorites = AddNoteToFavoritesUseCase(favoriteNoteRepository),
            deleteNoteFromFavorites = DeleteNoteFromFavoritesUseCase(favoriteNoteRepository),
            getFavoriteNotesUseCase = GetFavoriteNotesUseCase(favoriteNoteRepository)
        )
    }

}