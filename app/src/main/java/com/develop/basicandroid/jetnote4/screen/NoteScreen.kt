package com.develop.basicandroid.jetnote4.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.develop.basicandroid.R
import com.develop.basicandroid.jetnote4.components.NoteButton
import com.develop.basicandroid.jetnote4.components.NoteInputText
import com.develop.basicandroid.jetnote4.components.NoteRow
import com.develop.basicandroid.jetnote4.model.Note


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes :List<Note>,
    onAddNote : (Note) -> Unit,
    onRemoveNote : (Note) -> Unit
){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(6.dp)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app4_name)
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon"
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFDADFE3)
            )
        )
        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = title,
                label = "Hello",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                }
            )
            NoteInputText(
                text = description,
                label = "Add a Note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                }
            )
            NoteButton(
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(
                            Note(
                                title = title,
                                description = description
                            )
                        )
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
        HorizontalDivider()
        LazyColumn {
            items(notes){ note ->
                NoteRow(
                    note = note,
                    onClick = {
                        onRemoveNote(note)
                    }
                )
            }
        }
    }
}

