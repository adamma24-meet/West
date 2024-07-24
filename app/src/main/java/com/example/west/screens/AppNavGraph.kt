package com.example.west.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.west.screens.HomePageScreen
import com.example.west.screens.LoginScreen
import com.example.west.screens.MapScreen
import com.example.west.screens.SignUpScreen

@Composable
fun AppNavGraph(navController: NavHostController, context: Context) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomePageScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
        composable("map") {
            MapScreen(context)
        }
    }
}
