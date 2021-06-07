package com.setschedule.setticket.view.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.setschedule.setticket.R
import com.setschedule.setticket.viewmodel.EventsViewModel

class LikeFragment: Fragment()
{
    var eventVM: EventsViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var v: View = inflater.inflate(R.layout.fragment_like,container,false)
        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventVM = ViewModelProvider(requireActivity()).get(EventsViewModel::class.java)
        eventVM!!.liked.observe(requireActivity(), Observer {
            Log.i("Liked","Liked Events:"+it.size);
        });

    }

    override fun onResume() {
        super.onResume()
        eventVM!!.getLiked()
    }
}