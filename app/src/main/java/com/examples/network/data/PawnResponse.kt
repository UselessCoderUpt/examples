package com.examples.network.data

import com.google.gson.annotations.SerializedName

// response is returned inside pawnitems array
data class PawnResponse(
    @SerializedName("pawnitems")
    val result: List<PawnItemDto>
)