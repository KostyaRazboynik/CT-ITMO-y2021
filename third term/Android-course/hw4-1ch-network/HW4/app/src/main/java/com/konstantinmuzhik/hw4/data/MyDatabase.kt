package com.konstantinmuzhik.hw4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.konstantinmuzhik.hw4.data.dao.MessageDAO
import com.konstantinmuzhik.hw4.data.entity.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun get(context: Context): MyDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, MyDatabase::class.java, "database").build()
            }

            return INSTANCE as MyDatabase
        }
    }

    abstract fun messageDAO(): MessageDAO
}
