package com.examples.domain.data

import com.examples.domain.local.PawnItemEntity
import com.examples.domain.local.TodaysRenewalEntity
import com.examples.network.data.PawnItemDto
import java.util.*

data class PawnItem(
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

    // mapper function to convert DTO object to Domain data model
    fun toPawnItemEntity(): PawnItemEntity {
        return PawnItemEntity(
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

    fun toTodaysRenewalEntity(): TodaysRenewalEntity {
        return TodaysRenewalEntity(
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