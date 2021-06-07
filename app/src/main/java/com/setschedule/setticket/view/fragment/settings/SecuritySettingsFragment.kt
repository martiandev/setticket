package com.setschedule.setticket.view.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.setschedule.setticket.R
import com.setschedule.setticket.data.source.local.PredictHQPreferences
import com.setschedule.setticket.viewmodel.TokenViewModel

class SecuritySettingsFragment:Fragment()
{
    var et_client_id:EditText? =  null
    var et_client_secret:EditText? =  null
    var tv_status: TextView? =  null
    var bt_generate: Button? =  null
    var tokenVM:TokenViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings_security,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.et_client_id =  view.findViewById(R.id.et_client_id)
        this.et_client_secret =  view.findViewById(R.id.et_client_secret)
        this.tv_status =  view.findViewById(R.id.tv_status)
        this.bt_generate =  view.findViewById(R.id.bt_generate)

        this.bt_generate!!.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                if(et_client_id!!.text.length>0&&et_client_secret!!.text.length>0)
                {
                    bt_generate!!.text = "Please wait"
                    bt_generate!!.isEnabled = false
                    tokenVM = ViewModelProvider(requireActivity()).get(TokenViewModel::class.java)

                    PredictHQPreferences.update((requireActivity()), PredictHQPreferences.TAG_CLIENT_ID,et_client_id!!.text.toString())
                    PredictHQPreferences.update((requireActivity()), PredictHQPreferences.TAG_CLIENT_SECRET,et_client_secret!!.text.toString())

                    tokenVM!!.token.observe(requireActivity(), Observer {
                        if(it!=null)
                        {
                            if(it.body()==null)
                            {
                                tv_status!!.setText(resources.getText(R.string.token_failed))
                            }
                            else
                            {
                                if(PredictHQPreferences.get((requireActivity()), PredictHQPreferences.TAG_TOKEN_TYPE)!=null)
                                {
                                    tv_status!!.setText(resources.getText(R.string.token_retrieved))


                                }
                                else
                                {
                                    tv_status!!.setText(resources.getText(R.string.token_failed))

                                }

                            }
                            bt_generate!!.text = "retrieve token"
                            bt_generate!!.isEnabled = true
                        }
                    })
                    tokenVM!!.generateToken(requireActivity())
                }

            }

        })
    }
}