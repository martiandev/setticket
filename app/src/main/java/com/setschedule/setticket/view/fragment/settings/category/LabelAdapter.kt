package com.setschedule.setticket.view.fragment.settings.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.setschedule.setticket.R
import com.setschedule.setticket.data.source.local.PredictHQPreferences

class LabelAdapter(): RecyclerView.Adapter<LabelViewHolder>()
{
    var labels: ArrayList<Label> = ArrayList()

    constructor(labels:ArrayList<Label>):this()
    {
        this.labels = labels
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_label,parent,false)
        return LabelViewHolder(v)
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        var label:Label = labels.get(position)
        holder.tv_name!!.text = labels.get(position).name
        holder.sw_enable!!.isChecked = PredictHQPreferences.isEnabled(holder.sw_enable!!.context,label!!.preferenceName!!) == true
        holder.sw_enable!!.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            PredictHQPreferences.update(
                holder.sw_enable!!.context,
                label.preferenceName!!,
                b)
        }
    }

    override fun getItemCount(): Int {
       return this.labels.size
    }



}