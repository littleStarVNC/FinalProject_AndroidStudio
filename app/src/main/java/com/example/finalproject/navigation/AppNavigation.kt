package com.example.finalproject.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("home") { HomeScreen(navController) }
        composable("lythuyet") { LyThuyetScreen(navController) }
        composable("bienbao") { BienBaoScreen(navController) }
        composable("meothi") { MeoThiScreen(navController) }
        composable("caccausai") { CacCauSaiScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }

        // Màn hình chọn đề
        composable("thisathach") {
            Column(modifier = Modifier.fillMaxSize()) {
                FeatureTopBar(title = "Thi sát hạch") {
                    navController.popBackStack()
                }

                ChonDeScreen(onDeSelected = { soDe ->
                    navController.navigate("thi_sat_hach/$soDe")
                })
            }
        }

        composable(
            "test/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            val categoryTitle = when (categoryId) {
                "concept_rules" -> "KHÁI NIỆM VÀ QUY TẮC"
                "culture_ethics" -> "VĂN HOÁ VÀ ĐẠO ĐỨC LÁI"
                else -> "Bài Kiểm Tra"
            }
            TestScreen(navController, categoryId, categoryTitle)
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

            Column(modifier = Modifier.fillMaxSize()) {
                FeatureTopBar(title = "Đề thi số $soDe") {
                    navController.popBackStack()
                }

                ThiSatHachScreen(deThi = deThi) { correctCount, isPassed ->
                    correct = correctCount
                    passed = isPassed
                    showResult = true
                }
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