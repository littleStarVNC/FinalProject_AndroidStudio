package com.example.finalproject.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalproject.screen.KetQuaDialog
import com.example.finalproject.util.getDeThi
import com.example.finalproject.screen.*

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("home") { HomeScreen(navController) }
        composable("lythuyet") { LyThuyetScreen(navController) }
        //composable("thisathach") { ThiSatHachScreen(navController) }
        composable("bienbao") { BienBaoScreen(navController) }
        composable("meothi") { MeoThiScreen(navController) }
        composable("caccausai") { CacCauSaiScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        // Màn hình chọn đề
        composable("thisathach") {
            ChonDeScreen(onDeSelected = { soDe ->
                navController.navigate("thi_sat_hach/$soDe")
            })
        }

// Màn hình thi sát hạch với số đề truyền vào
        composable(
            "thi_sat_hach/{soDe}",
            arguments = listOf(navArgument("soDe") { type = NavType.IntType })
        ) { backStackEntry ->
            val soDe = backStackEntry.arguments?.getInt("soDe") ?: 1
            val deThi = getDeThi(soDe) // Hàm trả về danh sách câu hỏi của đề

            var showResult by remember { mutableStateOf(false) }
            var correct by remember { mutableStateOf(0) }
            var passed by remember { mutableStateOf(false) }

            ThiSatHachScreen(deThi = deThi) { correctCount, isPassed ->
                correct = correctCount
                passed = isPassed
                showResult = true
            }

            KetQuaDialog(
                showDialog = showResult,
                correctAnswers = correct,
                passed = passed
            ) {
                showResult = false
                navController.popBackStack("thisathach", inclusive = false)
            }
        }

    }
}
