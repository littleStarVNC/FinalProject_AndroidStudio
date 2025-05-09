package com.example.finalproject.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.screen.*
import com.example.finalproject.screens.HomeScreen

@Composable
fun AppNavigation(innerPadding: PaddingValues) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("ly_thuyet") { LyThuyetScreen(navController) }
        composable("thi_sat_hach") { ThiSatHachScreen(navController) }
        composable("bien_bao") { BienBaoScreen(navController) }
        composable("meo_thi") { MeoThiScreen(navController) }
        composable("cac_cau_sai") { CacCauSaiScreen(navController) }
    }
}
