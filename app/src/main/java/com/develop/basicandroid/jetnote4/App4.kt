package com.develop.basicandroid.jetnote4

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develop.basicandroid.jetnote4.screen.NoteScreen
import com.develop.basicandroid.jetnote4.screen.viewmodels.NoteViewModel


@Preview
@Composable
fun App4(){
    /*
    val notes = remember {
        mutableStateListOf<Note>()
    } // Not Recommended Need of ViewModel
    NoteScreen(
        notes = notes,
        onAddNote = {
            notes.add(it)
        },
        onRemoveNote = {
            notes.remove(it)
        }
    )*/

    //Using ViewModels
   //  val noteViewModel : NoteViewModel  by viewModels() ---> Works Only in MainActivity
    NoteApp()
}

@Composable
fun NoteApp(noteViewModel: NoteViewModel = viewModel ()){
    val noteList = noteViewModel.getAllNotes()
    NoteScreen(
        notes = noteList,
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        }
    )
}