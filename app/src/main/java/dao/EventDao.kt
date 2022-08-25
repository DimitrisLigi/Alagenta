package dao

import androidx.lifecycle.LiveData
import androidx.room.*
import models.Event

@Dao
interface EventDao {
    //I don't care for all events. I care only for a specific day.
//    @Query("SELECT * FROM event_table")
//    fun getAllEvents(): LiveData<List<Event>>

    @Query("SELECT * FROM event_table WHERE event_date = :queryDate")
    fun findByDate(queryDate: String): LiveData<List<Event>>

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("DELETE  FROM event_table")
    suspend fun deleteAllEvents()

    @Update
    suspend fun updateEvent(event: Event)

}