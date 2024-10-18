package com.cooktogether.ui.shared.components.models.navigationBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Create
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name: String,
    val icon: ImageVector,
    val route: String,
) {
    companion object {
        val Home = BottomNavItem("Home", Icons.Filled.Home, "home")
        val Explore = BottomNavItem("Explorar", Icons.Filled.Search, "explore")
        val MyRecipes = BottomNavItem("Minhas Receitas", Icons.Rounded.Create, "my_recipes")
        val Profile = BottomNavItem("Perfil", Icons.Filled.Person, "profile")
        val Center = BottomNavItem("Adicionar", Icons.Filled.AddCircle, "add")
    }
}