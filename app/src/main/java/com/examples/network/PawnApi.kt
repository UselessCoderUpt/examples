package com.examples.network

import com.examples.network.data.CustomerResponse
import com.examples.network.data.PawnResponse
import retrofit2.http.GET

interface PawnApi {

    //Fetches pawn items that are not released to till date
    //https://script.google.com/macros/s/AKfycbw6bVFuM-tKXDW5xwMQxs53_q7oN58G1P2L_6U5RKzV_vQljM6tCOqx8VhZ9FVyX5dZ/exec
    @GET("AKfycbw6bVFuM-tKXDW5xwMQxs53_q7oN58G1P2L_6U5RKzV_vQljM6tCOqx8VhZ9FVyX5dZ/exec")
//    suspend fun getOsPawnItems(): List<PawnItemDto>
    suspend fun getOsPawnItems(): PawnResponse

    //Fetches outstanding renewal list that are pending as on last year's todays date
    //https://script.google.com/macros/s/AKfycbx8l3pWvjSvTVaWO2ibNAULJcwBKFHXnFAVZ9lssBUKCmYxab52DCZ6q8hxaRmA2GYR/exec
    @GET("AKfycbx8l3pWvjSvTVaWO2ibNAULJcwBKFHXnFAVZ9lssBUKCmYxab52DCZ6q8hxaRmA2GYR/exec")
    suspend fun getTodaysRenewalList(): PawnResponse

    //TODO: Customer list to be implemented

    //https://script.google.com/macros/s/AKfycbxQwhTWkc_GnxTkRanGFOJs0j6C-wX3a9nXIUM93jRWm0PVWNfMDuMZGWepYtks_Zam/exec
    @GET("AKfycbxQwhTWkc_GnxTkRanGFOJs0j6C-wX3a9nXIUM93jRWm0PVWNfMDuMZGWepYtks_Zam/exec")
    suspend fun getCustomers(): CustomerResponse
}