package com.setschedule.setticket.data.source.local

import android.content.Context
import android.content.SharedPreferences

class SearchPreferences
{

    //limit   -set by me              //id
    //offset -set by me
    //state - active - set by me

    //q

    //category
    //label

    //within
    //-radius
    //-unit km
    // lat
    // lon

    //mock
    //lat
    //lon

    companion object
    {
        private var TAG_SEARCH          = "search"
        private var TAG_LIMIT           = "limit" //int
        private var TAG_OFFSET          = "offset" //int

        //sort start

        fun getPreference(c: Context): SharedPreferences
        {
            return c.getSharedPreferences(TAG_SEARCH, Context.MODE_PRIVATE)
        }

        fun getEditor(c: Context): SharedPreferences.Editor
        {
            return getPreference(c).edit()
        }

        fun update(c: Context, tag:String, value:String)
        {
            getEditor(c).putString(tag,value).commit()
        }

        fun get(c: Context, tag:String):String?
        {
            var result:String? = null
            var fallback:String? = null
            return getPreference(c).getString(tag,fallback)
        }

    }
}