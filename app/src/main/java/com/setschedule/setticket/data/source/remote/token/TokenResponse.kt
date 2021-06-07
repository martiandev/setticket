package com.setschedule.setticket.data.source.remote.token

import com.google.gson.annotations.SerializedName

class TokenResponse
{
    @SerializedName("access_token")
    var accessToken:String? = null
    @SerializedName("token_type")
    var tokenType:String? = null
    var scope:String? = null
}