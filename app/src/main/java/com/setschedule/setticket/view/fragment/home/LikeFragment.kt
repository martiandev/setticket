package com.setschedule.setticket.view.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.setschedule.setticket.R
import com.setschedule.setticket.viewmodel.EventsViewModel

class LikeFragment: Fragment()
{
    var eventVM: EventsViewModel? = null
    var tv_message: TextView? = null
    var rv_events: RecyclerView? = null
    var adapter:EventEntityAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var v: View = inflater.inflate(R.layout.fragment_like,container,false)
        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tv_message = view.findViewById(R.id.tv_message)
        this.rv_events = view.findViewById(R.id.rv_events)
        this.rv_events!!.layoutManager = LinearLayoutManager(view.context)
        eventVM = ViewModelProvider(requireActivity()).get(EventsViewModel::class.java)
        eventVM!!.liked.observe(requireActivity(), Observer {
            if(it.size>0)
            {
                rv_events!!.visibility =  View.VISIBLE
                tv_message!!.visibility = View.GONE


                adapter = EventEntityAdapter(it)
                rv_events!!.adapter =adapter
            }
            else
            {
                rv_events!!.visibility = View.GONE
                tv_message!!.visibility = View.VISIBLE
            }
        });

    }

    override fun onResume() {
        super.onResume()
        eventVM!!.getLiked()
    }
}