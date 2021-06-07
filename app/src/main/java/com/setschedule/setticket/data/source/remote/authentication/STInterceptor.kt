package com.setschedule.setticket.data.source.remote.authentication

import android.content.Context
import android.util.Log
import com.setschedule.setticket.data.source.local.PredictHQPreferences
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class STInterceptor:Interceptor
{

    var context:Context? = null


    constructor(context: Context)
    {
        this.context = context

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var req:Request = chain.request()
        Log.d("INTERCEPTOR:","TYPE:"+PredictHQPreferences.get(context!!,PredictHQPreferences.TAG_TOKEN_TYPE))
        Log.d("INTERCEPTOR:","TOKEN:"+PredictHQPreferences.get(context!!,PredictHQPreferences.TAG_TOKEN))

        req = req.newBuilder().header("Authorization",PredictHQPreferences.get(context!!,PredictHQPreferences.TAG_TOKEN_TYPE)+" "+PredictHQPreferences.get(context!!,PredictHQPreferences.TAG_TOKEN)).build()
        return chain.proceed(req)
    }
}