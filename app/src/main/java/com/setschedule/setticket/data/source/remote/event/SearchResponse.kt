package com.setschedule.setticket.data.source.remote.event

import com.google.gson.annotations.SerializedName

class SearchResponse
{
    @SerializedName("count")
    var count:Int? = 0
    var overflow:Boolean? = false
    var next:String? = null
    var results = emptyArray<Event>()

}