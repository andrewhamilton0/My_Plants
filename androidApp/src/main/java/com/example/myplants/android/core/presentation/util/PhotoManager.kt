package com.example.myplants.android.core.presentation.util

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberPhotoPickerLauncher(onSelectedPhoto: (ByteArray?) -> Unit): () -> Unit {
    val context = LocalContext.current
    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                val inputStream = context.contentResolver?.openInputStream(uri)
                val byteArray = inputStream?.readBytes()
                inputStream?.close()
                onSelectedPhoto(byteArray)
            } else {
                onSelectedPhoto(null)
            }
        }
    )

    return {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}
