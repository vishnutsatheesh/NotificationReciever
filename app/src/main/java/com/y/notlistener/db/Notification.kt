package com.y.notlistener.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Notification(
    @PrimaryKey(autoGenerate = true) val key: Int?,
    val ttile: String?,
    var description: String?,
    var packageid: String?,
    var dattime: String?
) : Serializable
