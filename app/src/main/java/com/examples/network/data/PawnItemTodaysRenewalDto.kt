package com.examples.network.data

import com.examples.domain.data.PawnItem
import com.google.gson.annotations.SerializedName

data class PawnItemOSDto(
    @SerializedName("ID")
    val ID: Int,
    @SerializedName("Name")
    val Name: String,
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
        ID = ID,
        Date = null, // date not retrieved from api
        Name = Name,
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