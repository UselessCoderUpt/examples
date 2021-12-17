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
import retrofit2.HttpException
import java.io.IOException
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

        var osPawnItems = pawnDao.getOsPawnItems().value?.map { it.toPawnItem() }
        emit(Resource.Loading(osPawnItems))

        try {
            //convert each response dto to data model
            osPawnItems = pawnApi.getOsPawnItems().response.map { it.toPawnItem() }
            emit(Resource.Success(osPawnItems))
        } catch (e: HttpException) {
            Log.d(Constants.TAG, "UC Error" + e.message.toString())
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = osPawnItems
                )
            )
        } catch (e: IOException) {
            Log.d(Constants.TAG, "UC Error" + e.message.toString())
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = osPawnItems
                )
            )
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

        var pawnItems = pawnDao.getTodaysRenewals().value?.map { it.toPawnItem() }
        emit(Resource.Loading(pawnItems))

        try {
            //convert each response dto to data model
            pawnItems = pawnApi.getTodaysRenewalList().response.map { it.toPawnItem() }
            emit(Resource.Success(pawnItems))
        } catch (e: HttpException) {
            Log.d(Constants.TAG, "Error" + e.message.toString())
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = pawnItems
                )
            )
        } catch (e: IOException) {
            Log.d(Constants.TAG, "Error" + e.message.toString())
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = pawnItems
                )
            )
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

        var customers = pawnDao.getCustomers().value?.map { it.toCustomer() }
        emit(Resource.Loading(customers))
        try {
            //convert each response dto to data model
            customers = pawnApi.getCustomers().response.map { it.toCustomer() }
            emit(Resource.Success(customers))
        } catch (e: HttpException) {
            Log.d(Constants.TAG, "Error" + e.message.toString())
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = customers
                )
            )
        } catch (e: IOException) {
            Log.d(Constants.TAG, "Error" + e.message.toString())
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = customers
                )
            )
        }catch (e: Exception) {
            Log.d(Constants.TAG, "UC Error" + e.message.toString())
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "Unexpected error occurred!",
                    data = customers
                )
            )
        }

    }
}