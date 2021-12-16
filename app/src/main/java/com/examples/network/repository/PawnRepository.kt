package com.examples.network.repository

import com.examples.common.Resource
import com.examples.domain.data.Customer
import com.examples.domain.data.PawnItem
import kotlinx.coroutines.flow.Flow

//Fake repository can be used for testing with the help of interface implementation
interface PawnRepository {
    fun getOsPawnItems(): Flow<Resource<List<PawnItem>>>
    fun getTodaysRenewalList(): Flow<Resource<List<PawnItem>>>
    fun getCustomers(): Flow<Resource<List<Customer>>>
}