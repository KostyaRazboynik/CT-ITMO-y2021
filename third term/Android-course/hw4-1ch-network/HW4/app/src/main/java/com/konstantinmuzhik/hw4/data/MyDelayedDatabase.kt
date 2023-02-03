package com.konstantinmuzhik.hw4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.konstantinmuzhik.hw4.data.dao.DelayedMessageDAO
import com.konstantinmuzhik.hw4.data.entity.DelayedMessageEntity

@Database(entities = [DelayedMessageEntity::class], version = 1)
abstract class MyDelayedDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: MyDelayedDatabase? = null

        fun get(context: Context): MyDelayedDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(
                        context, MyDelayedDatabase::class.java, "delayedDatabase"
                    ).build()
            }

            return INSTANCE as MyDelayedDatabase
        }
    }

    abstract fun delayedMessageDAO(): DelayedMessageDAO
}
