package com.examples.domain.data

import com.examples.domain.local.CustomerEntity
import java.util.*

data class Customer(
    val MobileNo: String,
    val Name: String,
    val Place: String,
    val Mobile2: String?,
    val Address: String
) {
    fun toCustomerEntity(): CustomerEntity {
        return CustomerEntity(
            MobileNo = MobileNo,
            Name = Name,
            Place = Place,
            Mobile2 = Mobile2,
            Address = Address
        )
    }
}