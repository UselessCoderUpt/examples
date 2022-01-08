package com.examples.network.repository

import android.util.Log
import com.examples.common.Constants
import com.examples.common.Resource
import com.examples.domain.data.Customer
import com.examples.domain.data.PawnItem
import com.examples.domain.local.CustomerEntity
import com.examples.domain.local.IPawnDao
import com.examples.network.IPawnApi
import com.examples.network.data.toCustomer
import com.examples.network.data.toPawnItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.lang.Exception
import javax.inject.Inject

class PawnRepositoryImpl @Inject constructor(
    private val pawnApi: IPawnApi,
    private val pawnDao: IPawnDao
) : IPawnRepository {

    //OS pawn items related member implementation
    override suspend fun insertOsPawnItem(pawnItems: List<PawnItem>) {
        pawnDao.insertOsPawnItem(pawnItems.map { it.toPawnItemEntity() })
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
        pawnDao.insertTodaysRenewal(pawnItems.map { it.toTodaysRenewalEntity() })
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
}