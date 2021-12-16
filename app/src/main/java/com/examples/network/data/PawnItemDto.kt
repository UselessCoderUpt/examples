package com.examples.network.data

import com.examples.domain.data.PawnItem
import com.examples.network.util.EmptyStringAsNullTypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.util.*

data class PawnItemDto(
    @SerializedName("ID")
    val ID: Int,
    @SerializedName("Date")
    val Date: Date,
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
    @JsonAdapter(EmptyStringAsNullTypeAdapter::class)
    val RenewIntAmount: Double?,
    @SerializedName("TOTAL RENEW")
    @JsonAdapter(EmptyStringAsNullTypeAdapter::class)
    val TotalAmount: Double?,
    @SerializedName("Total Mon")
    val TotalMonths: Double
)

// mapper function to convert DTO object to Domain data model
fun PawnItemDto.toPawnItem(): PawnItem {
    return PawnItem(
        ID = ID,
        Date = Date,
        Name = Name,
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