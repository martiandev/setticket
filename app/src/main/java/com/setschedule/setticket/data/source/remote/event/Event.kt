package com.setschedule.setticket.data.source.remote.event

import java.util.*

class Event
{
    var id:String? = null
    var title:String? = null
    var description:String? = null
    var category:String? = null
    var labels = emptyArray<String>()
    var duration:Int? = 0
    var start: Date? = null
    var scope:String? = null
    var country:String? = null
    var state:String? = null

}