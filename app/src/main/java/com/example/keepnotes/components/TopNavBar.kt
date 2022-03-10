package com.example.keepnotes.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.keepnotes.R
import com.example.keepnotes.ui.theme.whiteBackground
import kotlinx.coroutines.launch

@Composable
fun TopNavBar(drawerState: DrawerState) {

    val coroutineScope = rememberCoroutineScope()
    val search = remember { mutableStateOf(TextFieldValue()) }
    TopAppBar(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .clip(CircleShape),
        elevation = 4.dp,
        title = {
            BasicTextField(
                value = search.value,
                onValueChange = { search.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                textStyle = TextStyle(color = Color.DarkGray),
            )
        },
        backgroundColor = whiteBackground,
        navigationIcon = {
            IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_grid_icon),
                    contentDescription = "Menu",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(25.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_person_placeholder),
                    contentDescription = "Menu",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    )
}

//@Composable
//fun RoundedImage() {
//    Box(
//        modifier = Modifier.clip(CircleShape),
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_person_placeholder),
//            contentDescription = "Login Image",
//            modifier = Modifier
//                .height(34.dp)
//                .width(34.dp)
//        )
//    }
//}



