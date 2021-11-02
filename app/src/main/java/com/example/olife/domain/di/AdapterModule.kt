package com.example.olife.domain.di

import com.example.olife.data.model.Note
import com.example.olife.presentation.adapter.NotesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideNotesAdapter():NotesAdapter{
        var notes = ArrayList<Note>()
        notes.add(Note(1,"1","1"))
        notes.add(Note(2,"2","2"))
        notes.add(Note(3,"3","3"))
        notes.add(Note(4,"4","4"))
        notes.add(Note(5,"5","5"))
        return NotesAdapter()
    }
}