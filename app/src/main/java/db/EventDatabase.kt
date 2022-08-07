package db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dao.EventDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import models.Event

@Database(entities = [Event :: class],  version = 1)
abstract class EventDatabase : RoomDatabase(){

    abstract fun eventDao(): EventDao

    companion object{
        @Volatile
        private var INSTANCE: EventDatabase? = null

        @InternalCoroutinesApi
        fun getEventDatabase(context: Context): EventDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}