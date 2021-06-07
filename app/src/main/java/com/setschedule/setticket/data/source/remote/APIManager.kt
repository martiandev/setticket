package com.setschedule.setticket.data.source.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.setschedule.setticket.data.source.local.PredictHQPreferences
import com.setschedule.setticket.data.source.remote.authentication.STAuthInterceptor
import com.setschedule.setticket.data.source.remote.authentication.STInterceptor
import com.setschedule.setticket.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIManager
{
    companion object
    {
        private var INSTANCE:APIManager? = null

        fun get():APIManager?
        {
            if(INSTANCE==null)
            {
                INSTANCE = APIManager()
            }
            return INSTANCE
        }

    }
    public fun generateAuthRetrofit(context:Context):Retrofit
    {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        var gson: Gson = GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create()
        val client = OkHttpClient.Builder()
            .addInterceptor(STAuthInterceptor(
                PredictHQPreferences.get(context,PredictHQPreferences.TAG_CLIENT_ID)!!,
                PredictHQPreferences.get(context,PredictHQPreferences.TAG_CLIENT_SECRET)!!
            ))
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.AUTH_SERVER)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }
    public fun generateRetrofit(context:Context):Retrofit
    {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        var gson: Gson = GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create()
        val client = OkHttpClient.Builder()
            .addInterceptor(STInterceptor(context))
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.SERVER)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

}