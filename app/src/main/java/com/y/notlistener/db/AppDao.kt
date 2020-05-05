package com.y.notlistener.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AppDao {

    /**
     * Notification
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertNotification(notification: Notification)

    @Query("DELETE FROM Notification")
    fun deleteAllNotification()

//    @Query("SELECT * from Notification")
//    fun getAllNotification(): List<Notification>

    @Query("select * from Notification")
    fun getAllNotification(): LiveData<List<Notification>>

}