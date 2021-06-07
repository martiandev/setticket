package com.setschedule.setticket.view.app

import android.app.Application
import android.util.Log
import com.setschedule.setticket.data.database.STDatabase
import com.setschedule.setticket.data.source.local.PredictHQPreferences
import com.setschedule.setticket.data.source.remote.APIManager

class STApplication:Application()
{
    override fun onCreate() {
        super.onCreate()
        STDatabase.init(this)
        Log.i("SetTicket","Database Initiated");
    }
}