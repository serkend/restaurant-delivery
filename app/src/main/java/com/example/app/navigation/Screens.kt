package com.example.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val icon: ImageVector, val label: String) {
    object HomeScreen :
        Screens(route = "CategoryScreen", icon = Icons.Default.RestaurantMenu, label = "Меню")

    object ProfileScreen :
        Screens(route = "ProfileScreen", icon = Icons.Default.Person, label = "Профиль")

    object CartScreen :
        Screens(route = "BasketScreen", icon = Icons.Default.ShoppingBasket, label = "Корзина")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    companion object {
        val SCREEN_LIST = listOf(HomeScreen, ProfileScreen, CartScreen)
    }
}