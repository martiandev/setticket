package com.setschedule.setticket.data.source.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PredictHQPreferences
{
    companion object
    {
        private var TAG_SET_TICKET           = "set_ticket"
        public var  TAG_CLIENT_ID            = "client_id"
        public var  TAG_CLIENT_SECRET        = "client_secret"
        public var  TAG_TOKEN_TYPE           = "token_type"
        public var  TAG_TOKEN                = "token"
        public var  FALLBACK_CLIENT_ID       = "zkqgfwjVPxc"
        public var  FALLBACK_CLIENT_SECRET   = "xn7_2gspV_sSMwIKSeao8Kr6M7kMEkITN5_IS0KrSQSyTPYt1jR1sA"

        public var  TAG_CAT_SPORTS           = "sports"
        public var  TAG_CAT_CONCERT          = "concerts"
        public var  TAG_CAT_CONFERENCE       = "conferences"
        public var  TAG_CAT_EXPO             = "expos"

        public var  TAG_LABEL_SOCCER         = "soccer"
        public var  TAG_LABEL_BASKETBALL     = "basketball"
        public var  TAG_LABEL_HOCKEY         = "ice-hockey"
        public var  TAG_LABEL_RUGBY          = "rugby"
        public var  TAG_LABEL_BASEBALL       = "baseball"
        public var  TAG_LABEL_NFL            = "nfl"
        public var  TAG_LABEL_MLB            = "mlb"
        public var  TAG_LABEL_NHL            = "nhl"
        public var  TAG_LABEL_NBA            = "nba"
        public var  TAG_LABEL_CLOSED_D       = "closed-doors"
        public var  TAG_LABEL_OUT_D          = "outdoors"
        public var  TAG_LABEL_BUSINESS       = "business"
        public var  TAG_LABEL_EDUCATION      = "education"
        public var  TAG_LABEL_HEALTH         = "health"
        public var  TAG_LABEL_TECH           = "technology"
        public var  TAG_LABEL_EXPO_EDU       = "education_expo"
        public var  TAG_LABEL_EXPO_TEC       = "technology_expo"
        public var  TAG_LABEL_ENTERTAINMENT  = "entertainment"
        public var  TAG_LABEL_CAREER         = "career"
        public var  TAG_LABEL_ARTS           = "performing-arts"

        public var TAG_NEAR_ME              = "near_me"
        public var  TAG_RADIUS              = "radius"
        public var  TAG_LAT                 = "lat"
        public var  TAG_LON                 = "lon"

        fun getPreference(c: Context):SharedPreferences
        {
            return c.getSharedPreferences(TAG_SET_TICKET,Context.MODE_PRIVATE)
        }

        fun getEditor(c:Context):SharedPreferences.Editor
        {
            return getPreference(c).edit()
        }

        fun update(c:Context,tag:String,value:Boolean)
        {
            getEditor(c).putBoolean(tag,value).commit()

        }

        fun update(c:Context,tag:String,value:Int)
        {
            getEditor(c).putInt(tag,value).commit()
        }

        fun getAmount(c:Context,tag:String):Int
        {
           return getPreference(c).getInt(tag,1)
        }
        fun isEnabled(c:Context,tag:String):Boolean?
        {
            return getPreference(c).getBoolean(tag,false)
        }


        fun update(c:Context,tag:String,value:String)
        {
            getEditor(c).putString(tag,value).commit()
        }

        fun get(c:Context,tag:String):String?
        {

            var fallback:String? = "null"
            when(tag)
            {
                TAG_CLIENT_ID-> fallback= FALLBACK_CLIENT_ID
                TAG_CLIENT_SECRET-> fallback = FALLBACK_CLIENT_SECRET
                TAG_TOKEN->fallback = null
                TAG_TOKEN_TYPE->fallback = null
            }

            return getPreference(c).getString(tag,fallback)
        }

    }


}