package com.y.notlistener.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [Notification::class],
    version = 3,
    exportSchema = false
)

abstract class AppDB : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        private var instance: AppDB? = null

        fun getInstance(context: Context): AppDB? {
            if (instance == null) {
                synchronized(AppDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java, "db_emarat13"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return instance
        }

        fun destroyInstance() {
            instance = null
        }

    }


}
