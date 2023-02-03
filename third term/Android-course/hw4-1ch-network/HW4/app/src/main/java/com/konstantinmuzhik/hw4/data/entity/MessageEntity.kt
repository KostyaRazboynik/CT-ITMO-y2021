package com.konstantinmuzhik.hw4.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val imageId: Long? = null,
    val from: String,
    val to: String,
    val text: String? = null,
    val link: String? = null,
    val time: String
)
