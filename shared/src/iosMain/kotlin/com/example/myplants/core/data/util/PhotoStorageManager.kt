package com.example.myplants.core.data.util

import com.example.myplants.core.data.util.NSDataUtils.toByteArray
import com.example.myplants.core.data.util.NSDataUtils.toNSData
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import platform.Foundation.NSData
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import platform.Foundation.dataWithContentsOfFile
import platform.Foundation.writeToFile

actual class PhotoStorageManager(private val dispatcher: CoroutineDispatcher) {
    // TODO CHECK ON THIS
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
            val documentsDirectory = NSSearchPathForDirectoriesInDomains(
                NSDocumentDirectory,
                NSUserDomainMask,
                true
            ).firstOrNull() as? String

            return@withContext if (documentsDirectory != null) {
                val filePath = "$documentsDirectory/$fileName"
                NSData.dataWithContentsOfFile(filePath)?.toByteArray()
            } else {
                null
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun deleteByteArrayFromInternalStorage(fileName: String) {
        withContext(dispatcher) {
            val fileManager = NSFileManager.defaultManager
            fileManager.removeItemAtPath(fileName, null)
        }
    }

    private suspend fun saveByteArrayToInternalStorage(fileName: String, byteArray: ByteArray) {
        withContext(dispatcher) {
            val data = byteArray.toNSData()
            val documentsDirectory = NSSearchPathForDirectoriesInDomains(
                NSDocumentDirectory,
                NSUserDomainMask,
                true
            ).firstOrNull() as? String

            if (documentsDirectory != null) {
                val filePath = "$documentsDirectory/$fileName"
                data.writeToFile(filePath, true)
            } else {
                throw IllegalStateException("Unable to access documents directory")
            }
        }
    }
}
