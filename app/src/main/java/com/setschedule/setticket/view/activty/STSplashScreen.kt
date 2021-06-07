package com.setschedule.setticket.view.activty

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.setschedule.setticket.R
import com.setschedule.setticket.data.source.local.PredictHQPreferences
import com.setschedule.setticket.viewmodel.TokenViewModel

class STSplashScreen:FragmentActivity() {

    var tv_process:TextView? = null
    var tokenVM:TokenViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tv_process = findViewById(R.id.tv_process)
        checkToken()
    }

    fun checkToken()
    {
        if(PredictHQPreferences.get(this, PredictHQPreferences.TAG_TOKEN_TYPE)==null)
        {

            tv_process!!.setText(resources.getText(R.string.retrieve_token))
            tokenVM =  ViewModelProvider(this).get(TokenViewModel::class.java)
            tokenVM!!.token.observe(this, Observer {
                if(it!=null)
                {
                    if(PredictHQPreferences.get(this,PredictHQPreferences.TAG_TOKEN_TYPE)!=null)
                    {
                        tv_process!!.setText(resources.getText(R.string.token_retrieved))
                        launchMainActivity()

                    }
                    else
                    {
                        tv_process!!.setText(resources.getText(R.string.token_failed))
                        launchMainActivity()
                    }
                }
            })
            tokenVM!!.generateToken(this)
        }
        else
        {
            launchMainActivity()
        }
    }

    fun launchMainActivity()
    {

        var intent:Intent = Intent(this, STActivity::class.java)
        startActivity(intent)
        finish()
    }

}