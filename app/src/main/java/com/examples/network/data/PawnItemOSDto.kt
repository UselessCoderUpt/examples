package com.examples.network.data

import com.examples.domain.data.PawnItem
import com.google.gson.annotations.SerializedName

data class PawnItemOSDto(
    @SerializedName("ID")
    val LoanNo: Int,
    @SerializedName("Name")
    val CustomerName: String,
    @SerializedName("Place")
    val Place: String,
    @SerializedName("Mobile number")
    val MobileNo: String,
    @SerializedName("Item")
    val ItemName: String,
    @SerializedName("Type")
    val ItemType: String,
    @SerializedName("Weight")
    val Weight: Double,
    @SerializedName("Amount")
    val Amount: Double,
    @SerializedName("Renew INT")
    val RenewIntAmount: Double?,
    @SerializedName("TOTAL RENEW")
    val TotalAmount: Double?,
)

fun PawnItemOSDto.toPawnItem(): PawnItem {
    return PawnItem(
        LoanNo = LoanNo,
        Date = null, // date not retrieved from api
        CustomerName = CustomerName,
        Place = Place,
        MobileNo = MobileNo,
        ItemName = ItemName,
        ItemType = ItemType,
        Weight = Weight,
        Amount = Amount,
        RenewIntAmount = RenewIntAmount,
        TotalAmount = TotalAmount,
        TotalMonths = null // TotalMonths not retrieved from api
    )
}