package models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event(@PrimaryKey(autoGenerate = true) val eventID: Int?,
                 @ColumnInfo(name = "event_name") var eventName: String?,
                 @ColumnInfo(name = "event_address") var event_address: String?,
                 @ColumnInfo(name = "event_type") var eventAppointmentType: AppointmentType,
                 @ColumnInfo(name = "event_time") var eventTime: String?,
                 @ColumnInfo(name = "event_phone") var eventPhone: String?,
                 @ColumnInfo(name = "event_date") var eventDate: String?)
