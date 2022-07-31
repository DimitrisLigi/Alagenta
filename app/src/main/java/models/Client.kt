package models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client_table")
data class Client (@PrimaryKey(autoGenerate = true) val clientID: Int?,
                  @ColumnInfo(name = "client_address") var address: String,
                  @ColumnInfo(name = "client_area") var area: String,
                  @ColumnInfo(name = "client_name") var name: String,
                  @ColumnInfo(name = "client_ring_name") var ringName: String,
                  @ColumnInfo(name = "client_phone") var phone: String)
