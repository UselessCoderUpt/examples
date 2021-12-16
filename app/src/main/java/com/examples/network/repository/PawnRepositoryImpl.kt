package com.examples.network.repository

import android.util.Log
import com.examples.common.Constants
import com.examples.common.Resource
import com.examples.domain.data.Customer
import com.examples.domain.data.PawnItem
import com.examples.domain.local.PawnDB
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
    private val pawnDB: PawnDB
) : IPawnRepository {

    //fetch pawn items
    override fun getOsPawnItems(): Flow<Resource<List<PawnItem>>> = flow {

        var osPawnItems: List<PawnItem> = listOf()
        emit(Resource.Loading())
        //TODO: Fetch from db before APi Call
        //osPawnItems = dao.getOsPawnItems().map { it.toPawnItem() }
        //emit(Resource.Loading(data = osPawnItems))

        try {
            //convert each response dto to data model
            osPawnItems = pawnApi.getOsPawnItems().result.map { it.toPawnItem() }
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

    //fetch todays renewal list
    override fun getTodaysRenewalList(): Flow<Resource<List<PawnItem>>> = flow {

        var pawnItems: List<PawnItem> = listOf()
        emit(Resource.Loading())
        //TODO: Fetch from db before APi Call
        //pawnItems = dao.getOsPawnItems().map { it.toPawnItem() }
        //emit(Resource.Loading(data = pawnItems))

        try {
            //convert each response dto to data model
            pawnItems = pawnApi.getTodaysRenewalList().result.map { it.toPawnItem() }
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

    //fetch customer list
    override fun getCustomers(): Flow<Resource<List<Customer>>> = flow {

        var customers: List<Customer> = emptyList()
        emit(Resource.Loading())
        //TODO: Fetch from db before APi Call
        //customers = dao.getCustomers().map { it.toCustomer() }
        //emit(Resource.Loading(data = pawnItems))
        try {
            //convert each response dto to data model
            customers = pawnApi.getCustomers().result.map { it.toCustomer() }
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