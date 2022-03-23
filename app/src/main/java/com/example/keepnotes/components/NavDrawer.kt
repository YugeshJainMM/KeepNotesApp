package com.example.keepnotes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keepnotes.R
import com.example.keepnotes.ui.theme.DrawerBg
import kotlinx.coroutines.launch
import androidx.compose.material3.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(drawerState: DrawerState) {
    Column(
        modifier = Modifier
            .background(DrawerBg)
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 23.dp)
                .background(DrawerBg)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "Google Logo",
                modifier = Modifier
                    .height(23.dp)
                    .padding(start = 15.dp)
            )
            Column(
                modifier = Modifier.padding(top = 15.dp)
            ) {
                DrawerItem(title = "Notes", icon = R.drawable.ic_notes, drawerState = drawerState)
                DrawerItem(title = "Reminders", icon = R.drawable.ic_reminder, drawerState = drawerState)
                DrawerItem(title = "Create New Label", icon = R.drawable.ic_create_new_label, drawerState = drawerState)
                DrawerItem(title = "Archive", icon = R.drawable.ic_archive, drawerState = drawerState)
                DrawerItem(title = "Deleted", icon = R.drawable.ic_deleted, drawerState = drawerState)
                DrawerItem(title = "Settings", icon = R.drawable.ic_settings, drawerState = drawerState)
                DrawerItem(title = "Help & feedback", icon = R.drawable.ic_help, drawerState = drawerState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem(title: String, icon: Int, drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 12.dp)
            .clickable(onClick = {
                coroutineScope.launch {
                    drawerState.close()
                }
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            modifier = Modifier
                .padding(5.dp)
                .size(25.dp)
        )
        Text(text = title, fontSize = 13.sp, modifier = Modifier.padding(start = 8.dp))
    }
}