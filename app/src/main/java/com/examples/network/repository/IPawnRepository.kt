package com.examples.network.repository

import com.examples.common.Resource
import com.examples.domain.data.Customer
import com.examples.domain.data.PawnItem
import kotlinx.coroutines.flow.Flow

//Fake repository can be used for testing with the help of interface implementation
interface IPawnRepository {

    //OsPawnItems related members
    suspend fun insertOsPawnItem(pawnItems: List<PawnItem>)
    suspend fun deleteOsPawnItem(loanNo: Int)
    suspend fun getOsPawnItems(): Flow<Resource<List<PawnItem>>>

    //Today's Renewal list related members
    suspend fun insertTodaysRenewal(pawnItems: List<PawnItem>)
    suspend fun deleteTodaysRenewal(loanNo: Int)
    suspend fun getTodaysRenewalList(): Flow<Resource<List<PawnItem>>>

    //Customer related members
    suspend fun insertCustomer(customers: List<Customer>)
    suspend fun deleteCustomer(mobileNo: String)
    suspend fun getCustomers(): Flow<Resource<List<Customer>>>
}