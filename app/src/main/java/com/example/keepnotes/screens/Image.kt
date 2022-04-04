package com.example.keepnotes.screens

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.example.keepnotes.utils.CameraIntegration
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun Image() {
    var bitmap: Bitmap? = null
    Box {
        CameraIntegration(bitmaps = bitmap)
    }
}