package db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dao.ClientDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import models.Client

@Database(entities = [Client :: class], version = 1)
abstract class ClientDatabase: RoomDatabase() {

    abstract fun clientDao(): ClientDao

    @InternalCoroutinesApi
    companion object{
        @Volatile
        private var INSTANCE: ClientDatabase? = null

        fun getClientDatabase(context: Context): ClientDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   ClientDatabase::class.java,
                   "client_db"
               ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}