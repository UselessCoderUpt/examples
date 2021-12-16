package com.examples.network.data

import com.google.gson.annotations.SerializedName

// response is returned inside customers array
data class CustomerResponse(
    @SerializedName("customers")
    val result: List<CustomerDto>
)