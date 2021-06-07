package com.setschedule.setticket.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.setschedule.setticket.data.source.local.PredictHQPreferences
import com.setschedule.setticket.data.source.remote.APIManager
import com.setschedule.setticket.data.source.remote.token.TokenResponse
import com.setschedule.setticket.data.source.remote.token.TokenService
import com.setschedule.setticket.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TokenViewModel :ViewModel()
{
    var retrofit: Retrofit? = null

    var token = MutableLiveData<Response<TokenResponse>>()

    fun generateToken(context:Context)
    {
        this.retrofit=APIManager.get()!!.generateAuthRetrofit(context)
        var service = retrofit!!.create(TokenService::class.java)
        val call = service.generateToken(Constants.GRANT_TYPE, Constants.SCOPE)

        call.enqueue(object : Callback<TokenResponse>
        {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if(response.body()!=null)
                {
                    PredictHQPreferences.update(context!!, PredictHQPreferences.TAG_TOKEN,response.body()!!.accessToken!!)
                    PredictHQPreferences.update(context!!, PredictHQPreferences.TAG_TOKEN_TYPE,response.body()!!.tokenType!!)
                }

                token.postValue(response)
            }
            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                Log.d("TOKENVM","FAILED")
            }
        }
        );
    }

}