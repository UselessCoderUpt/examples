package com.examples.network.repository

import com.examples.common.Resource
import com.examples.common.ViewState
import com.examples.domain.data.Customer
import com.examples.domain.data.PawnItem
import kotlinx.coroutines.flow.Flow

//Fake repository can be used for testing with the help of interface implementation
interface ICustomerRepository {
    //Customer related members
    suspend fun insertCustomer(customers: List<Customer>)
    suspend fun deleteCustomer(mobileNo: String)
    fun getCustomers(): Flow<List<Customer>>
}