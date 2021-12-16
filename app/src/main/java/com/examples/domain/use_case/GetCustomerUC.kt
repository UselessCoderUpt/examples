package com.examples.domain.use_case

import com.examples.common.Resource
import com.examples.domain.data.Customer
import com.examples.network.repository.IPawnRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//UC - Use Case
class GetCustomerUC @Inject constructor(
    private val repository: IPawnRepository
) {
    operator fun invoke(): Flow<Resource<List<Customer>>> {
        return repository.getCustomers()
    }
}