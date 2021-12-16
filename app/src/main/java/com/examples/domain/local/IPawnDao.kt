package com.examples.domain.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IPawnDao {

    // Customer
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customers: List<CustomerEntity>)

    @Query("DELETE FROM Customer WHERE Id = :id")
    suspend fun deleteCustomer(id: Int)

    //@Query("SELECT * FROM Customer WHERE Name LIKE '%' || :name || '%'")
    @Query("SELECT * FROM Customer ORDER BY Name ASC")
    suspend fun getCustomers(): LiveData<List<CustomerEntity>>

    @Query("SELECT * FROM Customer WHERE Name LIKE '%' || :name || '%'")
    suspend fun getCustomersByName(name: String): LiveData<List<CustomerEntity>>


    // Pawn Item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOsPawnItem(osPawnItems: List<PawnItemEntity>)

    @Query("DELETE FROM OsPawnItem WHERE LoanNo = :id")
    suspend fun deleteOsPawnItem(id: Int)

    @Query("SELECT * FROM OsPawnItem ORDER BY Name ASC")
    suspend fun getOsPawnItems(): LiveData<List<CustomerEntity>>
}