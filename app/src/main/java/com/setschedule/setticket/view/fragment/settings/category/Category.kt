package com.setschedule.setticket.view.fragment.settings.category

class Category {
    var name:String? = null
    var preferenceName:String? = null
    var labels:ArrayList<Label> = ArrayList()

    constructor(name:String,preferenceName: String)
    {
        this.preferenceName = preferenceName
        this.name = name
    }

    fun addLabel(label: Label)
    {
        labels.add(label)
    }
}