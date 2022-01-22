package com.josegrillo.data.entity

import com.google.gson.annotations.SerializedName
import com.josegrillo.data.utils.makeSecurePath

data class ThumbnailDTO(
    @SerializedName("path")
    val path: String?,
    @SerializedName("extension")
    val extension: String?
) {
    override fun toString(): String {
        return if (!path.isNullOrEmpty() && !extension.isNullOrEmpty()) {
            "${path.makeSecurePath()}.$extension"
        } else {
            ""
        }
    }
}