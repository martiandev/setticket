package com.setschedule.setticket.view.fragment.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class HomePageAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity)
{

    var list:ArrayList<Fragment>?=null

    constructor(fragmentActivity: FragmentActivity,list:ArrayList<Fragment>):this(fragmentActivity)
    {
        this.list=list
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun createFragment(position: Int): Fragment {
        return list!!.get(position)
    }

}