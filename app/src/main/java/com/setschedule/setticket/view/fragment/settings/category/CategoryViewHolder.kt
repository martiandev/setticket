package com.setschedule.setticket.view.fragment.settings.category

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.setschedule.setticket.R

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    var tv_name: TextView? = itemView.findViewById(R.id.tv_name)
    var sw_enable: SwitchMaterial? = itemView.findViewById(R.id.sw_enable)
    var rv_labels:RecyclerView? = itemView.findViewById(R.id.rv_labels)

}