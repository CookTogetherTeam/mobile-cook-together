package com.cooktogether.domain.models

data class Recipe(
    val id: Int,
    val user: String,
    val title: String,
    val ingredients: List<Ingredients>,
    val preparationMethod: List<String>,
    val image: String? = null,
    val category: Int,
    val categoryName: String
)
