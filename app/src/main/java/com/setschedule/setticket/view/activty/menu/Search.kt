package com.setschedule.setticket.view.activty.menu

import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import com.setschedule.setticket.R
import com.setschedule.setticket.data.source.remote.event.Event
import com.setschedule.setticket.data.source.remote.event.EventArrayAdapter
import com.setschedule.setticket.viewmodel.EventsViewModel

class Search:MenuActivity(),SwipeFlingAdapterView.onFlingListener
{
    var eventVM:EventsViewModel? = null
    var et_search:EditText?= null
    var tv_instructions:TextView?= null
    var bt_search: Button?= null
    var offset:Int = 0
    var q:String? = null
    var swiper:SwipeFlingAdapterView? =null
    var eventAdapter:EventArrayAdapter? = null
    var list:ArrayList<Event> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        eventVM = ViewModelProvider(this).get(EventsViewModel::class.java)
        eventVM!!.searchResult.observe(this, Observer {
            bt_search!!.isEnabled = true
            bt_search!!.text = "Search"
            offset = it.next
            if (it.results.size < 1)
            {
                tv_instructions!!.text = "No Events Found"
            }
            else
            {
                tv_instructions!!.text = getString(R.string.instructions)
            }
            list.addAll(it.results)
            eventAdapter!!.notifyDataSetChanged()
        })
        swiper = findViewById(R.id.swiper)
        tv_instructions = findViewById(R.id.tv_instructions)
        swiper!!.setFlingListener(this)
        eventAdapter = EventArrayAdapter(baseContext,R.layout.adapter_event,list)
        swiper!!.adapter = eventAdapter
        eventAdapter!!.notifyDataSetChanged();
        bt_search = findViewById(R.id.bt_search)
        et_search = findViewById(R.id.et_search)
        bt_search!!.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
               search()
            }

        });

        setActionBar()
    }

    fun search()
    {
        Log.i("SEARCH-ss",et_search!!.text.toString()+" vs "+q)
        list.clear()
        eventAdapter!!.notifyDataSetChanged()
        swiper!!.isEnabled = false
        swiper!!.isFocusableInTouchMode =false
        if(q!=null)
        {
            if(!q.equals(et_search!!.text.toString()))
            {
                offset = 0
            }
        }

        q = et_search!!.text.toString()
        if(q!!.length<1)
        {
            q=null
        }
        bt_search!!.isEnabled = false
        bt_search!!.text = "Wait"
        eventVM!!.search(q,offset,baseContext)
    }




    override fun title(): Int {
        return R.string.action_search
    }


    override fun removeFirstObjectInAdapter() {
        list.removeAt(0)
        eventAdapter!!.notifyDataSetChanged()
    }

    override fun onLeftCardExit(p0: Any?) {
        eventVM!!.save(p0!! as Event,true)

    }

    override fun onRightCardExit(p0: Any?) {
        eventVM!!.save(p0!! as Event,false)

    }

    override fun onAdapterAboutToEmpty(p0: Int) {

    }

    override fun onScroll(p0: Float) {

    }

}