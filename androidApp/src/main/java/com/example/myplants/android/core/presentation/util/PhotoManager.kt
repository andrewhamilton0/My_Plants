package com.example.myplants.android.core.presentation.util

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.myplants.featureplant.domain.plant.Photo

@Composable
fun rememberPhotoPickerLauncher(onSelectedPhoto: (Photo?) -> Unit): () -> Unit {
    val context = LocalContext.current
    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                val inputStream = context.contentResolver?.openInputStream(uri)
                val byteArray = inputStream?.readBytes()
                inputStream?.close()
                onSelectedPhoto(byteArray?.let { Photo(byteArray = it) })
            } else {
                onSelectedPhoto(null)
            }
        }
    )

    return {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}
