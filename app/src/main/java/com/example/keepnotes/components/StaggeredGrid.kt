package com.example.keepnotes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StaggeredGrid() {
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
                staggeredText.forEach { text ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        backgroundColor = Color.White,
                        border = BorderStroke(0.1.dp, Color.Gray),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = text,
                            color = Color.Gray,
                            modifier = Modifier.padding(16.dp)
                        )
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
    "Everybody wants to be famous, but nobody wants to do the work. I live by that. You grind hard so you can play hard. At the end of the day, you put all the work in, and eventually it’ll pay off. It could be in a year, it could be in 30 years. Eventually, your hard work will pay off. — Kevin Hart",
    "Life imposes things on you that you can’t control, but you still have the choice of how you’re going to live through this. — Celine Dion",
    "Life is a succession of lessons which must be lived to be understood. — Helen Keller",
    "My mama always said, life is like a box of chocolates. You never know what you’re gonna get. — Forrest Gump"
)