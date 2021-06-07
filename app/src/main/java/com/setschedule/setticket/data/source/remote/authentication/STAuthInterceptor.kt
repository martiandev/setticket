package com.setschedule.setticket.data.source.remote.authentication

import android.util.Log
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class STAuthInterceptor:Interceptor
{

    var credentials:String? = null
    var id:String? = null
    var secret:String? = null

    constructor(id:String,secret:String)
    {
        this.id = id
        this.secret = secret
        this.credentials= Credentials.basic(id,secret)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var req:Request = chain.request()
        req = req.newBuilder().header("Authorization",credentials!!).build()
        return chain.proceed(req)
    }
}