package com.develop.basicandroid.jetnote4.screen.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.develop.basicandroid.jetnote4.data.NoteData
import com.develop.basicandroid.jetnote4.model.Note

class NoteViewModel : ViewModel() {
    private var noteList = mutableStateListOf<Note>()
    init {
        noteList.addAll(NoteData().loadNotes())
    }
    fun addNote(note : Note){
        noteList.add(note)
    }
    fun removeNote(note : Note){
        noteList.remove(note)

    }
    fun getAllNotes() : List<Note>{
        return noteList
    }
}