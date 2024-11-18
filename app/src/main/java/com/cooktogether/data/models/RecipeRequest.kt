package com.cooktogether.data.models

import com.google.gson.annotations.SerializedName

data class RecipeRequest(
    val title: String,
    val category: Int,
    val ingredients: List<Ingredient>,
    @SerializedName("text_area")
    val textArea: List<String>,
    @SerializedName("image_base64")
    val imageBase64: String? = null
)

data class Ingredient(
    val name: String,
    val quantity: Double,
    val unit: String
)

