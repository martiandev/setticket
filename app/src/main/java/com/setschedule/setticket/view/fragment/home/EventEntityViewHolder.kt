package com.setschedule.setticket.view.fragment.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.setschedule.setticket.R

class EventEntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    var tv_title: TextView? = itemView.findViewById(R.id.tv_title)
    var tv_category: TextView? = itemView.findViewById(R.id.tv_category)
    var tv_date: TextView? = itemView.findViewById(R.id.tv_date)
    var tv_time: TextView? = itemView.findViewById(R.id.tv_time)
}