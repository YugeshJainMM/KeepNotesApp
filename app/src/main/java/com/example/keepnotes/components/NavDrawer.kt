package com.example.keepnotes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keepnotes.R
import com.example.keepnotes.ui.theme.gBLue
import com.example.keepnotes.ui.theme.gGreen
import com.example.keepnotes.ui.theme.gRed
import com.example.keepnotes.ui.theme.gYellow
import kotlinx.coroutines.launch

@Composable
fun NavDrawer(scaffoldState: ScaffoldState) {
    Column {
        val logoFontSize = 30.dp
        Row(
            modifier = Modifier.padding(start = 15.dp, top = 20.dp, bottom = 36.dp)
        ) {
            Text(text = "G", fontSize = 30.sp, fontWeight = FontWeight.Medium, color = gBLue)
            Text(text = "o", fontSize = 30.sp, fontWeight = FontWeight.Medium, color = gRed)
            Text(text = "o", fontSize = 30.sp, fontWeight = FontWeight.Medium, color = gYellow)
            Text(text = "g", fontSize = 30.sp, fontWeight = FontWeight.Medium, color = gBLue)
            Text(text = "l", fontSize = 30.sp, fontWeight = FontWeight.Medium, color = gGreen)
            Text(text = "e", fontSize = 30.sp, fontWeight = FontWeight.Medium, color = gRed)
            Text(text = " Keep", fontSize = 30.sp, fontWeight = FontWeight.Normal)
        }

        DrawerItem(title = "Notes", icon = R.drawable.ic_notes, scaffoldState = scaffoldState)
        DrawerItem(title = "Reminders", icon = R.drawable.ic_reminder, scaffoldState = scaffoldState)
        DrawerItem(title = "Create New Label", icon = R.drawable.ic_create_new_label, scaffoldState = scaffoldState)
        DrawerItem(title = "Archive", icon = R.drawable.ic_archive, scaffoldState = scaffoldState)
        DrawerItem(title = "Deleted", icon = R.drawable.ic_deleted, scaffoldState = scaffoldState)
        DrawerItem(title = "Settings", icon = R.drawable.ic_settings, scaffoldState = scaffoldState)
        DrawerItem(title = "Help & feedback", icon = R.drawable.ic_help, scaffoldState = scaffoldState)
    }
}

@Composable
fun DrawerItem(title: String, icon: Int, scaffoldState: ScaffoldState) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 12.dp)
            .clickable(onClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = title, modifier = Modifier.padding(5.dp).size(25.dp))
        Text(text = title, fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
    }
}