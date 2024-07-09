package com.example.myplants.core.data.util

expect class PhotoStorageManager {
    suspend fun saveByteArrayToInternalStorage(fileName: String, byteArray: ByteArray)
    suspend fun retrieveByteArrayFromInternalStorage(fileName: String): ByteArray?
}