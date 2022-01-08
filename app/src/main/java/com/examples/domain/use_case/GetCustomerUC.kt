package com.examples.domain.use_case

import androidx.lifecycle.asLiveData
import com.examples.common.Resource
import com.examples.domain.data.Customer
import com.examples.network.repository.ICustomerRepository
import com.examples.network.repository.IPawnRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//UC - Use Case
class GetCustomerUC @Inject constructor(
    private val repository: ICustomerRepository
) {
    operator fun invoke(): Flow<List<Customer>> {
        return repository.getCustomers()
    }
}