package com.setschedule.setticket.view.fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.setschedule.setticket.R
import com.setschedule.setticket.data.database.EventEntity
import java.lang.Exception
import java.text.SimpleDateFormat

class EventEntityAdapter(): RecyclerView.Adapter<EventEntityViewHolder>()
{

    var events:List<EventEntity> = listOf()

    constructor(events:List<EventEntity>):this()
    {
        this.events = events
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventEntityViewHolder {
        var v: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_event_entity,parent,false)
        return EventEntityViewHolder(v)
    }

    override fun onBindViewHolder(holder: EventEntityViewHolder, position: Int) {
        var ev:EventEntity = this.events.get(position)
        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                Toast.makeText(holder.itemView.context,"EVENTS:"+ev.title,Toast.LENGTH_LONG).show()
            }
        })
        holder.tv_title!!.text = ev.title
        holder.tv_category!!.text = ev.category
        try {

            var dateFormat:SimpleDateFormat = SimpleDateFormat("MMM dd")
            var timeFormat:SimpleDateFormat = SimpleDateFormat("hh:mm aa")
            holder.tv_date!!.text = dateFormat.format(ev.start)
            holder.tv_time!!.text = timeFormat.format(ev.start)

        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }



    }

    override fun getItemCount(): Int {
      return  this.events.size
    }

}