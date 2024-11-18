package com.cooktogether.data.models

import com.google.gson.annotations.SerializedName

data class IngredientsResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: Double,
    @SerializedName("unit")
    val unit: String
)

