package com.example.myplants.featureplant.domain.plant

import com.example.myplants.core.data.util.UuidProvider

data class Photo(
    val key: String = UuidProvider.getUuid(),
    val byteArray: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Photo

        if (key != other.key) return false
        if (!byteArray.contentEquals(other.byteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + byteArray.contentHashCode()
        return result
    }
}
