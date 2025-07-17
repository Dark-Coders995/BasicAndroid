package com.develop.basicandroid.jetnote4.data

import com.develop.basicandroid.jetnote4.model.Note

class NoteData {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(
                title = "Anroid",
                description = "Good Dev",
            ),

            Note(
                title = "Anroid",
                description = "Good Dev"
            ),

            Note(
                title = "Anroid",
                description = "Good Dev"
            )
        )
    }
}