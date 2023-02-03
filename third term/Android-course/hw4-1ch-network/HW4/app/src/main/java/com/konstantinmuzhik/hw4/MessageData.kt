package com.konstantinmuzhik.hw4

import android.graphics.Bitmap
import com.fasterxml.jackson.annotation.JsonProperty

data class MessageData (
    @JsonProperty ("from")
    val from: String,
    val to: String,
    @JsonProperty ("data")
    val data: Data,
    @JsonProperty("time")
    val time: String,
    val id: Long? = null
)

data class Data(
    val Image: ImageData? = null,
    val Text: TextData? = null,
)

data class ImageData(
    val link: String = "",
    var bitmap: Bitmap? = null,
)

data class TextData(
    val text: String = ""
)