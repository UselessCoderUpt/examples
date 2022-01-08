package com.examples.network.repository

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.examples.common.Constants
import com.examples.common.Resource
import com.examples.domain.data.Customer
import com.examples.domain.local.ICustomerDao
import com.examples.network.IPawnApi
import com.examples.network.data.toCustomer
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val pawnApi: IPawnApi,
    private val customerDao: ICustomerDao
) : ICustomerRepository {

    //Customer related member implementation
    override suspend fun insertCustomer(customers: List<Customer>) {
        customerDao.insertCustomer(customers.map { it.toCustomerEntity() })
    }

    override suspend fun deleteCustomer(mobileNo: String) {
        customerDao.deleteCustomer(mobileNo)
    }

    override fun getCustomers(): Flow<List<Customer>> {
        Log.d(Constants.TAG, "Inside getCustomers repo threadName : ${Thread.currentThread().name}")

        // load from dao
        //TODO: run in separate async coroutine deferred job or flow???


        //customers = pawnApi.getCustomers().response.map { it.toCustomer() }

        val customers = customerDao
            .getCustomers()
            .map { list ->
                list.map { it.toCustomer() }
            }


        return customers

        /*val result: Flow<Result> = flow {
            val data = someSuspendingFunction()
            emit(data)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = Result.Loading
        )
*/
    }

    /*suspend fun getBlogs(): kotlinx.coroutines.flow.Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    } */
}