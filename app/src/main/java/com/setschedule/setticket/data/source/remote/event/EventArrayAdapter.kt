package com.setschedule.setticket.data.source.remote.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.setschedule.setticket.R
import kotlinx.android.synthetic.main.adapter_event.view.*
import java.lang.Exception
import java.text.SimpleDateFormat

class EventArrayAdapter(context: Context, resource: Int, objects: List<Event>) : ArrayAdapter<Event>(context, resource, objects)
{



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var event:Event? = getItem(position)
        var v:View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_event,parent,false)

        var tv_tittle:TextView = v.findViewById(R.id.tv_title)
        var tv_date:TextView = v.findViewById(R.id.tv_date)
        var tv_category:TextView = v.findViewById(R.id.tv_category)
        var tv_description:TextView = v.findViewById(R.id.tv_description)

        tv_tittle!!.text = event!!.title
        try
        {
            var sf = SimpleDateFormat("MMM dd, yyyy hh:mm aa")
            tv_date!!.text = sf.format(event!!.start)
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }

        tv_category.text="Category:"+event!!.category!!+"\n"
        if(event!!.description!!.length>0)
        {
            tv_description.text=event.description
        }
        else
        {
            tv_description.text=tv_description.text.toString()+"No Description"
        }




        return v
    }
}