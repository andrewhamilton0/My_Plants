package com.example.myplants.core.data.util

expect class PhotoStorageManager {
    suspend fun upsertByteArrayToInternalStorage(fileName: String, byteArray: ByteArray)
    suspend fun retrieveByteArrayFromInternalStorage(fileName: String): ByteArray?
    suspend fun deleteByteArrayFromInternalStorage(fileName: String)
}
