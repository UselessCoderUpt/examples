package com.examples.domain.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.examples.domain.data.PawnItem
import java.util.*

@Entity(tableName = "TodaysRenewal")
data class TodaysRenewalEntity(
    @PrimaryKey
    val LoanNo: Int,
    val Date: Date?,
    val CustomerName: String,
    val Place: String,
    val MobileNo: String,
    val ItemName: String,
    val ItemType: String,
    val Weight: Double,
    val Amount: Double,
    val RenewIntAmount: Double?,
    val TotalAmount: Double?,
    val TotalMonths: Int?
    ){
    // mapper function to convert entity object to Domain data model
    fun toPawnItem(): PawnItem {
        return PawnItem(
            LoanNo = LoanNo,
            Date = Date,
            CustomerName = CustomerName,
            Place = Place,
            MobileNo = MobileNo,
            ItemName = ItemName,
            ItemType = ItemType,
            Weight = Weight,
            Amount = Amount,
            RenewIntAmount = RenewIntAmount,
            TotalAmount = TotalAmount,
            TotalMonths = TotalMonths
        )
    }
}