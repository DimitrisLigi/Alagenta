package dao

import androidx.room.*
import models.Client

@Dao
interface ClientDao {
    @Query("SELECT * FROM client_table")
    fun getAllClients(): List<Client>

    @Query("SELECT * FROM client_table WHERE client_name LIKE :queryName LIMIT 1")
    suspend fun findByName(queryName: String): Client

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClient(client: Client)

    @Delete
    suspend fun deleteClient(client: Client)

    @Query("DELETE FROM client_table")
    suspend fun deleteAllClients()


}