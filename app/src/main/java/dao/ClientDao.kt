package dao

import androidx.room.*
import models.Client

@Dao
interface ClientDao {
    @Query("SELECT * FROM client_table")
    fun getAllClients(): List<Client>

    @Query("SELECT * FROM client_table WHERE client_phone LIKE :queryPhone LIMIT 1")
    suspend fun findByPhone(queryPhone: String): Client

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClient(client: Client)

    @Delete
    suspend fun deleteClient(client: Client)

    @Query("DELETE FROM client_table")
    suspend fun deleteAllClients()


}