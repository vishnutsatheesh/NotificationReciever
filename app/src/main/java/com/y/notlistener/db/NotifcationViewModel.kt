package com.y.notlistener.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.y.notlistener.db.AppDB.Companion.getInstance

class NotifcationViewModel(application: Application) :
    AndroidViewModel(application) {
    val itemNofication: LiveData<List<Notification>>
    private val appDatabase: AppDao

    init {
        appDatabase = getInstance(application.baseContext)!!.appDao()
        itemNofication = appDatabase.getAllNotification()
    }


    fun getitemNofication(): LiveData<List<Notification>> {
        return itemNofication
    }
}