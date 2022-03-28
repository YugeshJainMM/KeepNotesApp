package com.example.keepnotes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.keepnotes.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.keepnotes.ui.theme.whiteBackground
import com.example.keepnotes.viewmodel.NotesViewModel

@Composable
fun BottomNavBar() {
    BottomAppBar(
        modifier = Modifier.padding(bottom = 40.dp).clip(RoundedCornerShape(30.dp)),
        containerColor = whiteBackground,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                BottomNavItem(icon = R.drawable.ic_outline_checkbox, description = "New List")
                BottomNavItem(icon = R.drawable.ic_outline_brush, description = "Draw Pad")
                BottomNavItem(icon = R.drawable.ic_outlined_mic, description = "Voice Notes")
                BottomNavItem(icon = R.drawable.ic_outline_image, description = "Add Image")
            }
        }
    )
}

@Composable
fun BottomNavItem(
    icon: Int,
    description: String
){
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = description,
            modifier = Modifier.size(25.dp)
        )
    }
}

@Composable
fun FabBtn(
    notesViewModel: NotesViewModel
) {
    FloatingActionButton(
        onClick = { notesViewModel.openDialogState.value = true },
        shape = RoundedCornerShape(30),
        containerColor = whiteBackground
    ) {
        Image(
            painter = painterResource(id = R.drawable.fab_button),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
    }
}