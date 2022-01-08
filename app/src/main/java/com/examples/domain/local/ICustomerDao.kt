package com.examples.domain.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ICustomerDao {

    // Customer
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customers: List<CustomerEntity>)

    @Query("DELETE FROM Customer WHERE MobileNo = :mobileNo")
    suspend fun deleteCustomer(mobileNo: String)

    @Query("SELECT * FROM Customer ORDER BY Name ASC")
    fun getCustomers(): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM Customer WHERE Name LIKE '%' || :name || '%'")
    suspend fun getCustomersByName(name: String): List<CustomerEntity>

}