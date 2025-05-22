package com.example.finalproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.screen.*

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("lythuyet") { LyThuyetScreen(navController) }
        composable("thisathach") { ThiSatHachScreen(navController) }
        composable("bienbao") { BienBaoScreen(navController) }
        composable("meothi") { MeoThiScreen(navController) }
        composable("caccausai") { CacCauSaiScreen(navController) }
    }
}
