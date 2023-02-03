package com.konstantinmuzhik.hw4.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.konstantinmuzhik.hw4.data.entity.MessageEntity

@Dao
interface MessageDAO {

    @Insert(onConflict = IGNORE)
    fun saveMessage(messageEntity: MessageEntity)

    @Query("SELECT * FROM messages WHERE `id`=:id")
    fun getById(id: Long?): Long

    @Query("SELECT * FROM messages ORDER BY id ASC")
    fun getAllMessages(): List<MessageEntity>

}
