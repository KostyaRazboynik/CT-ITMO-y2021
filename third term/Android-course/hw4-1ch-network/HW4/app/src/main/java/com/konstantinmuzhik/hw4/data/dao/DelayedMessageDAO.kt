package com.konstantinmuzhik.hw4.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.konstantinmuzhik.hw4.data.entity.DelayedMessageEntity

@Dao
interface DelayedMessageDAO {

    @Insert(onConflict = IGNORE)
    fun saveDelayedMessage(delayedMessageEntity: DelayedMessageEntity)

    @Query("SELECT * FROM delayedMessages ORDER BY id ASC")
    fun getAllDelayedMessages(): List<DelayedMessageEntity>

    @Query("DELETE FROM delayedMessages WHERE id=:id")
    fun delete(id: Int)

}
