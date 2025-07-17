package com.develop.basicandroid.jetnote4.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.develop.basicandroid.jetnote4.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text :String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
){
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier
            .padding(
                top = 9.dp,
                bottom = 9.dp
            ),
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        maxLines = maxLine,
        label = {
            Text(
                text = label
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            onImeAction()
            keyboardController?.hide()
        }
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text :String,
    onClick: () -> Unit,
    enabled: Boolean = true
){
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = CircleShape
    ) {
        Text(
            text = text
        )
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note : Note,
    onClick: () -> Unit
){
    Surface(
        modifier = modifier
            .padding(
                4.dp
            )
            .clip(
                RoundedCornerShape(
                    topEnd = 33.dp ,
                    bottomStart = 33.dp)
            )
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        tonalElevation = 6.dp
    ){
        Column(
            modifier = modifier
                .clickable{
                    onClick()
                }
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d , MMM")),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}