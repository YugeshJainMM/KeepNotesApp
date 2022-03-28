package com.example.keepnotes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
                    it?.content.let { text ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            containerColor = Color.White,
                            border = BorderStroke(0.1.dp, Color.Gray),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = text.toString(),
                                color = Color.Gray,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

val staggeredText = listOf(
    "Many of life’s failures are people who did not realize how close they were to success when they gave up. – Thomas A. Edison",
    "Your time is limited, so don’t waste it living someone else’s life. Don’t be trapped by dogma – which is living with the results of other people’s thinking. – Steve Jobs",
    "In order to write about life first you must live it. – Ernest Hemingway",
    "Sing like no one’s listening, love like you’ve never been hurt, dance like nobody’s watching, and live like it’s heaven on earth. - Many Sources",
)