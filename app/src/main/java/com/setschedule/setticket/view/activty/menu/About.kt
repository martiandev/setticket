package com.setschedule.setticket.view.activty.menu

import android.os.Bundle

import com.setschedule.setticket.R

class About:MenuActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setActionBar()
    }

    override fun title(): Int {
        return R.string.action_about
    }

}