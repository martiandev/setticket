package com.setschedule.setticket.data.source.remote.event

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface EventService {




    @GET("/v1/events/")
    fun getEventsResponse(
            @Query("category")category:String?,
            @Query("label")label:String?,
            @Query("limit")limit:Int?,
            @Query("offset")offset:Int?,
            @Query("within")within:String?,
            @Query("active.gte")active:String?,
            @Query("start.gte")start:String?,
            @Query("q")q:String?,
            @Query("sort")sort:String?
    ): Call<SearchResponse>

    @GET("/v1/events/count/")
    fun getCounts(@Query("category")category:String?, @Query("within")within:String?): Call<ResponseBody>

    //GET /v1/events/calendar?start.gte=2021-06-01&start.lte=2021-06-30 HTTP/1.1
    //    /v1/events/calendar?start.gte=2021-06-05
    @GET("/v1/events/calendar/")
    fun getalendar(@Query("category")category:String?, @Query("within")within:String?,@Query("start.gte")start  :String?,@Query("start.lte")end  :String?): Call<ResponseBody>

}