package com.examples.network.repository

import android.util.Log
import com.examples.common.Constants
import com.examples.common.Resource
import com.examples.domain.data.Customer
import com.examples.domain.data.PawnItem
import com.examples.domain.local.IPawnDao
import com.examples.network.IPawnApi
import com.examples.network.data.toCustomer
import com.examples.network.data.toPawnItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class PawnRepositoryImpl @Inject constructor(
    private val pawnApi: IPawnApi,
    private val pawnDao: IPawnDao
) : IPawnRepository {

    //OS pawn items related member implementation
    override suspend fun insertOsPawnItem(pawnItems: List<PawnItem>) {
        pawnDao.insertOsPawnItem(pawnItems.map { it.toPawnItemEntity()})
    }

    override suspend fun deleteOsPawnItem(loanNo: Int) {
        pawnDao.deleteOsPawnItem(loanNo)
    }

    override suspend fun getOsPawnItems(): Flow<Resource<List<PawnItem>>> = flow {

        var osPawnItems = pawnDao.getOsPawnItems().map { it.toPawnItem() }
        emit(Resource.Loading(osPawnItems))

        try {
            //convert each response dto to data model
            osPawnItems = pawnApi.getOsPawnItems().response!!.map { it.toPawnItem() }
            emit(Resource.Success(osPawnItems))
        } catch (e: Exception) {
            Log.d(Constants.TAG, "UC Error" + e.message.toString())
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "Unexpected error occurred!",
                    data = osPawnItems
                )
            )
        }
    }

    //Today's Renewal items related member implementation
    override suspend fun insertTodaysRenewal(pawnItems: List<PawnItem>) {
        pawnDao.insertTodaysRenewal(pawnItems.map{ it.toTodaysRenewalEntity()})
    }

    override suspend fun deleteTodaysRenewal(loanNo: Int) {
        pawnDao.deleteTodaysRenewal(loanNo)
    }

    override suspend fun getTodaysRenewalList(): Flow<Resource<List<PawnItem>>> = flow {

        var pawnItems = pawnDao.getTodaysRenewals().map { it.toPawnItem() }
        emit(Resource.Loading(pawnItems))

        try {
            //convert each response dto to data model
            pawnItems = pawnApi.getTodaysRenewalList().response!!.map { it.toPawnItem() }
            emit(Resource.Success(pawnItems))
        } catch (e: Exception) {
            Log.d(Constants.TAG, "UC Error" + e.message.toString())
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "Unexpected error occurred!",
                    data = pawnItems
                )
            )
        }
    }

    //Customer related member implementation
    override suspend fun insertCustomer(customers: List<Customer>) {
        pawnDao.insertCustomer(customers.map { it.toCustomerEntity()})
    }

    override suspend fun deleteCustomer(mobileNo: String) {
        pawnDao.deleteCustomer(mobileNo)
    }

    override suspend fun getCustomers(): Flow<Resource<List<Customer>>> = flow {
        Log.d(Constants.TAG, "Inside getCustomers repo")

        var customers = pawnDao.getCustomers().map { it.toCustomer() }
        Log.d(Constants.TAG, "Customer count from dao" + customers.size)

        emit(Resource.Loading(customers))
        try {
            Log.d(Constants.TAG, "Inside getCustomers repo")

            //convert each response dto to data model
            customers = pawnApi.getCustomers().response!!.map { it.toCustomer() }
            emit(Resource.Success(customers))
        } catch (e: Exception) {
            Log.d(Constants.TAG, "UC Error" + e.message.toString())
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "Unexpected error occurred!",
                    data = customers
                )
            )
        }
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