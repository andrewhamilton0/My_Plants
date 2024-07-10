package com.example.myplants.core.data.util

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File

actual class PhotoStorageManager(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher
) {

    actual suspend fun upsertByteArrayToInternalStorage(
        fileName: String,
        byteArray: ByteArray
    ) {
        withContext(dispatcher) {
            deleteByteArrayFromInternalStorage(fileName)
            saveByteArrayToInternalStorage(fileName, byteArray)
        }
    }

    actual suspend fun retrieveByteArrayFromInternalStorage(fileName: String): ByteArray? {
        return withContext(dispatcher) {
            val file = File(context.filesDir, fileName)
            return@withContext if (file.exists()) {
                file.readBytes()
            } else {
                null
            }
        }
    }
    actual suspend fun deleteByteArrayFromInternalStorage(fileName: String) {
        withContext(dispatcher) {
            val file = File(context.filesDir, fileName)
            if (file.exists()) file.delete()
        }
    }

    private suspend fun saveByteArrayToInternalStorage(
        fileName: String,
        byteArray: ByteArray
    ) {
        withContext(dispatcher) {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write(byteArray)
            }
        }
    }
}
