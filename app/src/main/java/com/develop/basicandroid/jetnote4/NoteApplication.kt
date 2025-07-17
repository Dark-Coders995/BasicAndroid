package com.develop.basicandroid.jetnote4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApplication : Application() {
}

// In Manifest Add these under application
// <android:name = ".NoteApplication"

// Add @AndroidEntryPoint in MainActivity.kt