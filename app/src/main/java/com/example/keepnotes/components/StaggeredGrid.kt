package com.example.keepnotes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.Note
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaggeredGrid(
    note: List<Note?>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            StaggeredVerticalGridItem(
                numColumns = 2,
                modifier = Modifier.padding(5.dp)
            ) {
                note.forEach {
                    it?.let { text ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            containerColor = Color.White,
                            border = BorderStroke(0.1.dp, Color.Gray),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            if (it.title != null) {
                                Text(
                                    text = text.title.toString(),
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(
                                        top = 12.dp,
                                        start = 12.dp,
                                        end = 12.dp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Text(
                                text = text.content.toString(),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(
                                    12.dp
                                ),
                                maxLines = 11,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}