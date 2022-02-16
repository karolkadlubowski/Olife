package com.example.olife.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.olife.data.model.Note
import com.example.olife.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeNotesRepositoryAndroid : NotesRepository {

    private val noteList = mutableListOf<Note>()

    private val observableSavedNotes = MutableStateFlow<List<Note>>(noteList)

    override suspend fun saveNote(note: Note) {
        if(note.id!=null)
            noteList.add(note)
        else
        {
            val newNote = note.copy()
            newNote.id = noteList[noteList.size-1].id?.plus(1)
            noteList.add(newNote)
        }
    }

    override fun getSavedNotes(): Flow<List<Note>> {
        return observableSavedNotes
    }

    override suspend fun updateNote(note: Note) {
        if(note.id!=null)
            for(i in 0 until noteList.count()){
                if(noteList[i].id==note.id){
                    noteList[i].title=note.title
                    noteList[i].content=note.content
                }
            }
    }

    override suspend fun deleteNote(note: Note) {
        noteList.remove(note)
    }
}