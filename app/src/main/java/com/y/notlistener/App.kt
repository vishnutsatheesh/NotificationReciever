package com.y.notlistener

import android.app.Application
import com.y.notlistener.db.AppDB
import com.y.notlistener.db.AppDao

class App : Application() {

    lateinit var dao: AppDao
    override fun onCreate() {
        super.onCreate()
        instance = this

        dao = AppDB.getInstance(applicationContext)!!.appDao()
    }



    fun getAppDAO(): AppDao {
        return dao
    }

    companion object {
        lateinit var instance: App
    }


}


