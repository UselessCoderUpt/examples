package com.examples.network.data

import com.examples.domain.data.Customer
import com.examples.domain.data.PawnItem
import com.examples.network.util.EmptyStringAsNullTypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.util.*

data class CustomerDto(
    @SerializedName("Mobile")
    val MobileNo: String,
    @SerializedName("Name")
    val Name: String,
    @SerializedName("Place")
    val Place: String,
    @SerializedName("Mobile2")
    @JsonAdapter(EmptyStringAsNullTypeAdapter::class)
    val Mobile2: String?,
    @SerializedName("Address")
    val Address: String
)

// mapper function to convert DTO object to Domain data model
fun CustomerDto.toCustomer(): Customer {
    return Customer(
        MobileNo = MobileNo,
        Name = Name,
        Place = Place,
        Mobile2 = Mobile2,
        Address = Address
    )
}