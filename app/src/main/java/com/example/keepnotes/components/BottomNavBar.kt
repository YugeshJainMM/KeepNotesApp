package com.example.keepnotes.components

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.keepnotes.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.keepnotes.destinations.AddNoteScreenDestination
import com.example.keepnotes.destinations.MainCameraContentDestination
import com.example.keepnotes.ui.theme.whiteBackground
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.coroutineScope

@Composable
fun BottomNavBar(navigator: DestinationsNavigator) {
    BottomAppBar(
        modifier = Modifier
            .padding(bottom = 40.dp)
            .clip(RoundedCornerShape(30.dp)),
        containerColor = whiteBackground,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                BottomNavItem(
                    icon = R.drawable.ic_outline_checkbox,
                    description = "New List",
                    navigator
                )
                BottomNavItem(
                    icon = R.drawable.ic_outline_brush,
                    description = "Draw Pad",
                    navigator
                )
                BottomNavItem(
                    icon = R.drawable.ic_outlined_mic,
                    description = "Voice Notes",
                    navigator
                )
                BottomNavItem(
                    icon = R.drawable.ic_outline_image,
                    description = "Add Image",
                    navigator
                )
            }
        }
    )
}

@OptIn(
    ExperimentalPermissionsApi::class, coil.annotation.ExperimentalCoilApi::class,
    kotlinx.coroutines.ExperimentalCoroutinesApi::class
)
@Destination
@Composable
fun BottomNavItem(
    icon: Int,
    description: String,
    navigator: DestinationsNavigator
) {
    var bitmap: Bitmap? = null
    IconButton(onClick = {
        navigator.navigate(MainCameraContentDestination)
    }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = description,
            modifier = Modifier.size(25.dp)
        )
    }
}

@Composable
fun FabBtn(
    navigator: DestinationsNavigator
) {
    FloatingActionButton(
        onClick = { navigator.navigate(AddNoteScreenDestination) },
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