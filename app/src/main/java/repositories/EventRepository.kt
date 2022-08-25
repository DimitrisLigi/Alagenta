package repositories

import androidx.lifecycle.LiveData
import dao.EventDao
import models.Event

class EventRepository(private val eventDao: EventDao, private val queryDate: String) {

    val allEventsBySpecificDate: LiveData<List<Event>> = eventDao.findByDate(queryDate)

    suspend fun insertEvent(event: Event){
        eventDao.insertEvent(event)
    }

    suspend fun deleteEvent(event: Event){
        eventDao.deleteEvent(event)
    }

    suspend fun updateEvent(event: Event){
        eventDao.updateEvent(event)
    }


}