package com.setschedule.setticket.data.source.remote.token

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

//sample api : "https://api.predicthq.com/v1/events/?category=sports&label=nba&limit=50&offset=50"

interface TokenService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/token")
    fun generateToken(@Field("grant_type")grant_type:String,@Field("scope")scope:String ): Call<TokenResponse>

}