package com.examples.network

import com.examples.BuildConfig
import com.examples.network.data.CustomerResponse
import com.examples.network.data.PawnResponse
import retrofit2.http.GET

interface IPawnApi {

    //Fetches pawn items that are not released to till date
    @GET(BuildConfig.API_KEY_PAWNITEMS)
    suspend fun getOsPawnItems(): PawnResponse

    //Fetches outstanding renewal list that are pending as on last year's todays date
    @GET(BuildConfig.API_KEY_TODAYSOS)
    suspend fun getTodaysRenewalList(): PawnResponse

    @GET(BuildConfig.API_KEY_CUSTOMERS)
    suspend fun getCustomers(): CustomerResponse

    /*suspend fun allPlants(): List<Plant> = withContext(Dispatchers.Default) {
        delay(1500)
        val result = sunflowerService.getAllPlants()
        result.shuffled()
    }*/
}