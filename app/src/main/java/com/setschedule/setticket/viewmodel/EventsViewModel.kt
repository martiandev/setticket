package com.setschedule.setticket.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.setschedule.setticket.data.database.EventEntity
import com.setschedule.setticket.data.database.STDatabase
import com.setschedule.setticket.data.source.local.PredictHQPreferences
import com.setschedule.setticket.data.source.remote.APIManager
import com.setschedule.setticket.data.source.remote.event.Event
import com.setschedule.setticket.data.source.remote.event.EventService
import com.setschedule.setticket.data.source.remote.event.SearchResponse
import com.setschedule.setticket.data.source.remote.event.SearchResults
import com.setschedule.setticket.view.fragment.settings.category.Category
import com.setschedule.setticket.view.fragment.settings.category.Label
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventsViewModel:ViewModel()
{
    var retrofit: Retrofit? = null
    var searchResult = MutableLiveData<SearchResults>()
    var liked = MutableLiveData<List<EventEntity>>()
    var unliked = MutableLiveData<List<EventEntity>>()

    var temp:ArrayList<Event> = ArrayList()

    //Utility
    fun getDate():String?
    {
        var date: Date = Date()
        var dateString:String?=null
        try {
            var sf:SimpleDateFormat= SimpleDateFormat("yyyy-MM-dd")
            dateString = sf.format(date)
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
        return dateString
    }

    fun instantiateCategories():ArrayList<Category>
    {
        var categories:ArrayList<Category> = ArrayList()
        var sports: Category = Category("Sports", PredictHQPreferences.TAG_CAT_SPORTS)

        sports.addLabel(Label("Soccer",PredictHQPreferences.TAG_LABEL_SOCCER))
        sports.addLabel(Label("Basketball",PredictHQPreferences.TAG_LABEL_BASKETBALL))
        sports.addLabel(Label("Ice Hockey",PredictHQPreferences.TAG_LABEL_HOCKEY))
        sports.addLabel(Label("Rugby",PredictHQPreferences.TAG_LABEL_RUGBY))
        sports.addLabel(Label("Baseball",PredictHQPreferences.TAG_LABEL_BASEBALL))
        sports.addLabel(Label("NFL",PredictHQPreferences.TAG_LABEL_NFL))
        sports.addLabel(Label("MLB",PredictHQPreferences.TAG_LABEL_MLB))
        sports.addLabel(Label("NHL",PredictHQPreferences.TAG_LABEL_NHL))
        sports.addLabel(Label("NBA",PredictHQPreferences.TAG_LABEL_NBA))
        sports.addLabel(Label("Closed Door",PredictHQPreferences.TAG_LABEL_CLOSED_D))
        sports.addLabel(Label("Outdoor",PredictHQPreferences.TAG_LABEL_OUT_D))


        var conference: Category = Category("Conferences", PredictHQPreferences.TAG_CAT_CONFERENCE)
        conference.addLabel(Label("Business",PredictHQPreferences.TAG_LABEL_BUSINESS))
        conference.addLabel(Label("Education",PredictHQPreferences.TAG_LABEL_EDUCATION))
        conference.addLabel(Label("Health",PredictHQPreferences.TAG_LABEL_HEALTH))
        conference.addLabel(Label("Technology",PredictHQPreferences.TAG_LABEL_TECH))




        var expo: Category = Category("Expos", PredictHQPreferences.TAG_CAT_EXPO)
        expo.addLabel(Label("Education",PredictHQPreferences.TAG_LABEL_EXPO_EDU))
        expo.addLabel(Label("Technology",PredictHQPreferences.TAG_LABEL_EXPO_TEC))
        expo.addLabel(Label("Entertainment",PredictHQPreferences.TAG_LABEL_ENTERTAINMENT))
        expo.addLabel(Label("Career",PredictHQPreferences.TAG_LABEL_CAREER))
        expo.addLabel(Label("Performing Arts",PredictHQPreferences.TAG_LABEL_ARTS))

        var concert: Category = Category("Concerts", PredictHQPreferences.TAG_CAT_CONCERT)

        categories.add(sports)
        categories.add(conference)
        categories.add(expo)
        categories.add(concert)
        return categories
    }
    fun checkLength(value:String):String?
    {
        if(value.length<1)
        {
            return null
        }
        return value
    }
    fun addComa(value:String):String?
    {
        if(value.length>1)
        {
            return value+","
        }
        return value
    }
    //Parameters
    fun getCategories(context:Context,categories:ArrayList<Category>):String?
    {
        var result:String? = ""
        for(c in categories) {
            if (PredictHQPreferences.isEnabled(context, c.preferenceName!!) == true)
            {
                result = addComa(result!!)
                result = result+c.preferenceName
            }
        }
        return checkLength(result!!)
    }
    fun getLabels(context:Context,categories:ArrayList<Category>):String?
    {
        var result:String? = ""
        for(c in categories) {
            if (PredictHQPreferences.isEnabled(context, c.preferenceName!!) == true)
            {
              for(l in c.labels)
              {
                  if (PredictHQPreferences.isEnabled(context, l.preferenceName!!) == true)
                  {
                      result = addComa(result!!)
                      result = result+l.preferenceName
                  }
              }
            }
        }
        return checkLength(result!!)
    }
    fun getNearMe(context:Context):String?
    {
        var result:String? = ""

        if (PredictHQPreferences.isEnabled(context,PredictHQPreferences.TAG_NEAR_ME) == true)
        {
            result = ""+PredictHQPreferences.getAmount(context,PredictHQPreferences.TAG_RADIUS)+"km@"
            if(PredictHQPreferences.get(context,PredictHQPreferences.TAG_LAT)!=null&&PredictHQPreferences.get(context,PredictHQPreferences.TAG_LAT)!!.length>0)
            {
                result = result+PredictHQPreferences.get(context,PredictHQPreferences.TAG_LAT)
            }
            else
            {
                return null
            }
            if(PredictHQPreferences.get(context,PredictHQPreferences.TAG_LON)!=null&&PredictHQPreferences.get(context,PredictHQPreferences.TAG_LON)!!.length>0)
            {

                result =result+","+PredictHQPreferences.get(context,PredictHQPreferences.TAG_LON)
            }
            else
            {
                return null
            }
        }

        return checkLength(result!!)
    }

    //Queries
    fun getCount(context: Context)
    {
        this.retrofit= APIManager.get()!!.generateRetrofit(context)
        var service = retrofit!!.create(EventService::class.java)
        val call = service.getCounts(null,"5km@14.6042000,120.9822000")
        call.enqueue(object : Callback<ResponseBody>
        {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("Events","BODY:"+response.body()?.string()!!)
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }

    fun getCalendar(context: Context)
    {
        this.retrofit= APIManager.get()!!.generateRetrofit(context)
        var service = retrofit!!.create(EventService::class.java)
        val call = service.getCounts("festivals,sports","5km@14.6042000,120.9822000")
        call.enqueue(object : Callback<ResponseBody>
        {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("Events","BODY:"+response.body()?.string()!!)
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }
    fun search(q:String?,offset:Int,context: Context)
    {
        var categories:ArrayList<Category> = instantiateCategories()
        var dateString:String? = getDate()
        this.retrofit= APIManager.get()!!.generateRetrofit(context)
        var service = retrofit!!.create(EventService::class.java)
        val call = service.getEventsResponse(
                getCategories(context,categories),
                getLabels(context,categories),
                10,
                offset,
                getNearMe(context),
                null,
                dateString,
                q,
                "start"
        )
        call.enqueue(object : Callback<SearchResponse>
        {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                var res:SearchResults = SearchResults()
                if(response.body()!=null)
                {

                    res.next = offset+10






                    res.results = response.body()!!.results.asList()
                }
                searchResult.postValue(res)

            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("SEARCH FAILED:",""+t.cause)
            }
        })
    }

    fun save(event:Event, isLike:Boolean)
    {
        var eventEntity:EventEntity = EventEntity(event,isLike)
        var db:STDatabase = STDatabase.get()!!
        db.getEventDAO().insert(eventEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getLiked()
    {
        var db:STDatabase = STDatabase.get()!!
        db.getEventDAO()
            .get(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    liked.postValue(it)
                },
                {

                }
            )
    }

    fun getUnLiked()
    {
        var db:STDatabase = STDatabase.get()!!
        db.getEventDAO()
            .get(0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    unliked.postValue(it)
                },
                {

                }
            )
    }
    fun getID(id:String):List<EventEntity>
    {
        var db:STDatabase = STDatabase.get()!!
        return db.getEventDAO().get(id)

    }

}