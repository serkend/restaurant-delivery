package com.example.app.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app.screens.cart_screen.CartScreen
import com.example.app.screens.home_screen.HomeScreen
import com.example.app.screens.home_screen.HomeViewModel

@Composable
fun Navigation(navController: NavHostController, viewModel: HomeViewModel,scaffoldState :ScaffoldState) {

    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(viewModel = viewModel, scaffoldState=scaffoldState)
        }
        composable(route = Screens.ProfileScreen.route) {}
        composable(route = Screens.CartScreen.route) {
            CartScreen()
        }
    }
}