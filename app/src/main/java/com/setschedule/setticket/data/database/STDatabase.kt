package com.setschedule.setticket.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.setschedule.setticket.data.source.remote.event.Event

@TypeConverters(DateConverter::class)
@Database(
        entities=arrayOf(EventEntity::class)  ,
        version=2
)
abstract class STDatabase : RoomDatabase()
{
    companion object{
        private var INSTANCE:STDatabase? = null

        fun init(context: Context):STDatabase?
        {
            if (INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        STDatabase::class.java, "SetTicket"
                ).build()
            }
            return INSTANCE
        }

        fun get():STDatabase?
        {
            return INSTANCE
        }

        fun destroyInstance()
        {
            INSTANCE = null
        }

    }

    abstract fun getEventDAO():EventDao
}