package com.example.keepnotes.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.keepnotes.R
import com.example.keepnotes.destinations.WelcomeScreenDestination
import com.example.keepnotes.ui.theme.whiteBackground
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(drawerState: DrawerState, onClick: Unit, navigator: DestinationsNavigator) {

    val coroutineScope = rememberCoroutineScope()
    val search = remember { mutableStateOf(TextFieldValue()) }
    SmallTopAppBar(
        modifier = Modifier
            .padding(start = 16.dp, top = 40.dp, end = 16.dp)
            .clip(CircleShape)
            .height(47.dp),
        title = {
            BasicTextField(
                value = search.value,
                onValueChange = { search.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                textStyle = TextStyle(color = Color.DarkGray),
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = whiteBackground),
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
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Login Image",
                    modifier = Modifier.size(25.dp)
                )
            }
            IconButton(onClick = {
                onClick
                navigator.navigate(WelcomeScreenDestination)
                Log.d("TAG", "Logged Out ")
            }) {
                RoundedImage()
            }
        }
    )
}

@Composable
fun RoundedImage() {
    Box(
        modifier = Modifier.clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.yugesh),
            contentDescription = "Login Image",
            modifier = Modifier.size(32.dp)
        )
    }
}