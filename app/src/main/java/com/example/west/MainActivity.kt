package com.example.west

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arcgismaps.ApiKey
import com.arcgismaps.ArcGISEnvironment
import com.example.west.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setApiKey()
        setContent {
            val navController = rememberNavController()
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen(navController = navController)
                }
            }
        }
    }

    private fun setApiKey() {
        ArcGISEnvironment.apiKey = ApiKey.create("AAPKc1d64c1fdde244438bc42afc0ff0518a1e0fmJyGRzQGhfHcTEaNx0UsJuHxetth5kXLZYVPuQubvlsk2DO2Mj9ji5bK8vMU")
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        if (currentRoute == "map")
        {
            (navController.context as MainActivity)
        }
    }

    AppNavGraph(navController = navController)
}
