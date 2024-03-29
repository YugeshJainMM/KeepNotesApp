package com.example.keepnotes.camera

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

val EMPTY_IMAGE_URI: Uri = Uri.parse("file://dev/null")

@OptIn(DelicateCoroutinesApi::class)
@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Destination
@Composable
fun MainCameraContent(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
    var imageUri by remember { mutableStateOf(EMPTY_IMAGE_URI) }
    val context = LocalContext.current as ComponentActivity
    if (imageUri != EMPTY_IMAGE_URI) {
        Box(modifier = modifier) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(imageUri),
                contentDescription = "Captured image"
            )
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxSize()
            ) {
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick = {
                        imageUri = EMPTY_IMAGE_URI
                    }
                ) {
                    Text("Remove image")
                }
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick = {
                        coroutineScope.launch {
                            val compressedImage = compressImage(
                                context,
                                imageUri
                            )
                            uploadPhoto(
                                compressedImage?.let {
                                    it
                                },
                                "keepNote$imageUri",
                                "image/jpg"
                            ) {
                                Log.d("TAG", it)
                            }
                        }
                    }
                ) {
                    Text("Upload Image")
                }
            }
        }
    } else {
        var showGallerySelect by remember { mutableStateOf(false) }
        if (showGallerySelect) {
            Log.d("TAG", "If statement")
        } else {
            Box(modifier = modifier) {
                CameraCapture(
                    modifier = modifier,
                    onImageFile = { file ->
                        imageUri = file.toUri()
                    }
                )
            }
        }
    }
}

private suspend fun uploadPhoto(
    uri: Uri?,
    name: String,
    mimeType: String?,
    callback: (url: String) -> Unit
) {
    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference
    val fileRef = storageRef.child("images/$name")

    val metadata = mimeType?.let {
        StorageMetadata.Builder()
            .setContentType(mimeType)
            .build()
    }
    if (metadata != null) {
        uri?.let {
            fileRef.putFile(it, metadata).await()
        }
    } else {
        uri?.let {
            fileRef.putFile(it).await()
        }
    }
    callback(fileRef.downloadUrl.await().toString())
}

private fun compressImage(context: ComponentActivity, uri: Uri): Uri? {
    val bitmap = if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(
            context.contentResolver,
            uri
        )
    } else {
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    }
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bytes)

    val path: String = MediaStore.Images.Media.insertImage(
        context.contentResolver,
        bitmap,
        "Title",
        null
    )
    return Uri.parse(path)
}