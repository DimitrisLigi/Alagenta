package dao

import androidx.room.*
import models.Event

@Dao
interface EventDao {
    @Query("SELECT * FROM event_table")
    fun getAllEvents(): List<Event>

    @Query("SELECT * FROM event_table WHERE event_time LIKE :queryDate LIMIT 1")
    suspend fun findByDate(queryDate: String): Event

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("DELETE FROM event_table")
    suspend fun deleteAllEvents()

}