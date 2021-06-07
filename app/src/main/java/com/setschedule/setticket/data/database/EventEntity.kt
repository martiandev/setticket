package com.setschedule.setticket.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.setschedule.setticket.data.source.remote.event.Event
import java.util.*

@Entity
data class EventEntity(@PrimaryKey var id:String)
{
    var title:String? = null
    var description:String? = null
    var category:String? = null
    var duration:Int? = 0
    var start: Date? = null
    var scope:String? = null
    var country:String? = null
    var state:String? = null
    var l:Int = 0

    constructor(e: Event?,isInterested:Boolean) : this("")
    {
        if(e!=null)
        {
            this.id = e.id!!
            this.title = e.title
            this.description = e.description
            this.category = e.category
            this.duration = e.duration
            this.start = e.start
            this.scope = e.scope
            this.country = e.country
            this.state = e.state
            if(isInterested)
            {
                this.l = 1
            }
            else{
                this.l = 0
            }
        }
    }
}