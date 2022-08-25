package viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import db.EventDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Dispatchers

import models.Event
import repositories.EventRepository


@OptIn(InternalCoroutinesApi::class)
class EventViewModel (application: Application, queryDate: String): AndroidViewModel(application){
    val allEventsBySelectedDate: LiveData<List<Event>>
    val repository: EventRepository

    init {
        val dao = EventDatabase.getEventDatabase(application).eventDao()
        repository = EventRepository(dao,queryDate)
        allEventsBySelectedDate = repository.allEventsBySpecificDate
    }
}