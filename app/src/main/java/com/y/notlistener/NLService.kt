package com.y.notlistener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.google.gson.Gson
import com.y.notlistener.db.Notification
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class NLService : NotificationListenerService() {
    private val TAG = this.javaClass.simpleName

    //  private var nlservicereciver: NLServiceReceiver? = null
    override fun onCreate() {
        super.onCreate()
//        nlservicereciver = NLServiceReceiver()
//        val filter = IntentFilter()
//        filter.addAction("com.y.notlistener.NOTIFICATION_LISTENER_SERVICE_EXAMPLE")
//        registerReceiver(nlservicereciver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        //    unregisterReceiver(nlservicereciver)
    }


    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val pack = sbn.packageName
        val ticker = sbn.notification.tickerText?.toString()


        Log.e(TAG, "pack :$pack")
        Log.e(TAG, "ticker :$ticker")
        var extras: Bundle? = sbn.notification.extras

        val gson = Gson()
        val jsonOb = JSONObject(gson.toJson(extras).toString())

        Log.e(TAG, "Bundle :${gson.toJson(extras)}")
        Log.e(TAG, "jsonOb :${jsonOb}")
        val title = jsonOb.getJSONObject("mMap").getString("android.title")
        val text = jsonOb.getJSONObject("mMap").getString("android.text")
        Log.e(TAG, "title :$title")
        Log.e(TAG, "text :$text")


        if (!pack.equals("android")) {
            App.instance.dao.InsertNotification(
                Notification(
                    null, "${title}", "${text}", "$pack", "${SimpleDateFormat(
                        "dd MMM yyyy, HH:mm:ss"
                    ).format(
                        Date()
                    )}"
                )
            )

            try {
                val i = Intent("com.y.notlistener.NOTIFICATION_LISTENER_SERVICE_EXAMPLE")
                sendBroadcast(i)
            } catch (e: Exception) {

            }
        }


    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        Log.i(TAG, "********** onNOtificationRemoved")
        Log.i(
            TAG,
            "ID :" + sbn.id + "\t" + sbn.notification.tickerText + "\t" + sbn.packageName
        )

    }

}