package com.setschedule.setticket.view.fragment.settings.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.setschedule.setticket.R
import com.setschedule.setticket.data.source.local.PredictHQPreferences

class CategoryAdapter(): RecyclerView.Adapter<CategoryViewHolder>()
{
    var categories: ArrayList<Category> = ArrayList()

    constructor(categories:ArrayList<Category>):this()
    {
        this.categories = categories
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_category,parent,false)
        return CategoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        var cat:Category = categories.get(position)
        holder.tv_name!!.text = cat.name
        holder.sw_enable!!.isChecked = PredictHQPreferences.isEnabled(holder.sw_enable!!.context,cat.preferenceName!!) == true
        holder.sw_enable!!.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            PredictHQPreferences.update(
                holder.sw_enable!!.context,
                cat.preferenceName!!,
            b)
            if(b)
            {
                holder.rv_labels!!.visibility= View.VISIBLE
            }
            else
            {
                holder.rv_labels!!.visibility= View.GONE

            }
        }

        holder.rv_labels!!.layoutManager = LinearLayoutManager(holder.rv_labels!!.context)
        holder.rv_labels!!.adapter = LabelAdapter(cat.labels)
        if(holder.sw_enable!!.isChecked)
        {
            holder.rv_labels!!.visibility= View.VISIBLE
        }
        else
        {
            holder.rv_labels!!.visibility= View.GONE

        }
    }

    override fun getItemCount(): Int {
       return this.categories.size
    }

}