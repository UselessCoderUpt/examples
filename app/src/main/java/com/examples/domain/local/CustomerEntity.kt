package com.examples.domain.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.examples.domain.data.Customer
import com.examples.network.data.CustomerDto
import java.util.*

@Entity(tableName = "Customer")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val Id: Int = 0,
    val MobileNo: String,
    val Name: String,
    val Place: String,
    val Mobile2: String?,
    val Address: String
) {
    // mapper function to convert entity object to Domain data model
    fun toCustomer(): Customer {
        return Customer(
            MobileNo = MobileNo,
            Name = Name,
            Place = Place,
            Mobile2 = Mobile2,
            Address = Address
        )
    }
}