package com.example.keepnotes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.keepnotes.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.keepnotes.ui.theme.whiteBackground

@Composable
fun BottomNavBar() {
    BottomAppBar(
        backgroundColor = whiteBackground,
        cutoutShape = RoundedCornerShape(20),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_checkbox),
                        contentDescription = "New List"
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_brush),
                        contentDescription = "Draw Pad"
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outlined_mic),
                        contentDescription = "Voice Notes"
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_image),
                        contentDescription = "Add Image"
                    )
                }
            }
        }
    )
}

@Composable
fun FabBtn() {
    FloatingActionButton(
        onClick = { },
        shape = RoundedCornerShape(20),
        backgroundColor = whiteBackground
    ) {
        Icon(
            Icons.Filled.Add,
            tint = Color.Red,
            contentDescription = "Add",
            modifier = Modifier.size(40.dp)
        )
    }
}

