package models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event(@PrimaryKey(autoGenerate = true) val eventID: Int?,
                @ColumnInfo(name = "event_address") var address: String?,
                @ColumnInfo(name = "event_type") var appointmentType: AppointmentType,
                @ColumnInfo(name = "event_time") var time: String?)
