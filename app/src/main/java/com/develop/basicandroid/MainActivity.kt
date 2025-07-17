package com.develop.basicandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.develop.basicandroid.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

// @AndroidEntryPoint --> Important for Hilt DI
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                /*
                val noteViewModel : NoteViewModel  by viewModels()
                NoteApp(noteViewModel = noteViewModel)
                 */
            }
        }
    }
}