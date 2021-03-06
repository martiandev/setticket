package com.setschedule.setticket.data.database

import androidx.room.*
import com.setschedule.setticket.data.source.remote.event.Event
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: EventEntity): Completable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data:Array<EventEntity>): Completable
    @Query("SELECT * FROM EventEntity")
    fun getAll(): Single<List<EventEntity>>
    @Delete
    fun delete(data: EventEntity): Completable
    @Query("DELETE FROM EventEntity")
    fun clear(): Completable

    @Query("DELETE FROM EventEntity where start < :date ")
    fun delete(date:Long): Completable
    @Query("SELECT * FROM EventEntity where l=:ls ORDER BY start ASC")
    fun get(ls:Int): Single<List<EventEntity>>

    @Query("SELECT * FROM EventEntity where id=:id ")
    fun get(id:String):List<EventEntity>

    @Query("SELECT * FROM EventEntity where id IN (:id)")
    fun get(id:ArrayList<String>): Single<List<EventEntity>>

}