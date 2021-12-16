package com.examples.domain.data

import java.util.*

data class PawnItem(
    val LoanNno: Int,
    val Date: Date?,
    val Name: String,
    val Place: String,
    val MobileNo: String,
    val ItemName: String,
    val ItemType: String,
    val Weight: Double,
    val Amount: Double,
    val RenewIntAmount: Double?,
    val TotalAmount: Double?,
    val TotalMonths: Double?
    )