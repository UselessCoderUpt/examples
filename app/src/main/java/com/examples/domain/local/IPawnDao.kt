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

    @Query("DELETE FROM Customer WHERE MobileNo = :mobileNo")
    suspend fun deleteCustomer(mobileNo: String)

    @Query("SELECT * FROM Customer ORDER BY Name ASC")
    suspend fun getCustomers(): List<CustomerEntity>

    @Query("SELECT * FROM Customer WHERE Name LIKE '%' || :name || '%'")
    suspend fun getCustomersByName(name: String): List<CustomerEntity>


    // OS Pawn Item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOsPawnItem(osPawnItems: List<PawnItemEntity>)

    @Query("DELETE FROM OsPawnItem WHERE LoanNo = :loanNo")
    suspend fun deleteOsPawnItem(loanNo: Int)

    @Query("SELECT * FROM OsPawnItem ORDER BY CustomerName ASC")
    suspend fun getOsPawnItems(): List<PawnItemEntity>

    // Today's renewal Pawn Items
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodaysRenewal(pawnItems: List<TodaysRenewalEntity>)

    @Query("DELETE FROM TodaysRenewal WHERE LoanNo = :loanNo")
    suspend fun deleteTodaysRenewal(loanNo: Int)

    @Query("SELECT * FROM TodaysRenewal ORDER BY CustomerName ASC")
    suspend fun getTodaysRenewals(): List<TodaysRenewalEntity>
}