package com.example.finalproject.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.finalproject.model.Question
import com.example.finalproject.screen.*
import com.example.finalproject.viewmodel.DeThiViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = false,
    onToggleDarkTheme: (Boolean) -> Unit = {}
) {
    val navController = rememberNavController()
    val cauSaiListState = remember { mutableStateOf<List<Pair<Question, String>>>(emptyList()) }

    NavHost(navController = navController, startDestination = "login") {
        composable("home") { HomeScreen(navController) }
        composable("lythuyet") { LyThuyetScreen(navController) }
        composable("bienbao") { BienBaoScreen(navController) }
        composable("meothi") { MeoThiScreen(navController) }

        composable("caccausai") {
            CacCauSaiScreen(
                navController = navController,
                cauSaiList = cauSaiListState.value
            )
        }

        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }

        composable("setting") {
            SettingScreen(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onToggleDarkTheme = onToggleDarkTheme,
                onBackClick = { navController.popBackStack() }
            )
        }

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

        composable(
            "thi_sat_hach/{soDe}",
            arguments = listOf(navArgument("soDe") { type = NavType.StringType })
        ) { backStackEntry ->
            val soDe = backStackEntry.arguments?.getString("soDe") ?: "de1"

            var showResult by remember { mutableStateOf(false) }
            var correct by remember { mutableStateOf(0) }
            var passed by remember { mutableStateOf(false) }

            Column(modifier = Modifier.fillMaxSize()) {
                FeatureTopBar(title = "Đề thi $soDe") {
                    navController.popBackStack()
                }

                ThiSatHachScreenFromFirestore(deId = soDe) { correctCount, isPassed, cauSaiList ->
                    correct = correctCount
                    passed = isPassed
                    cauSaiListState.value = cauSaiList
                    showResult = true
                }
            }

            KetQuaDialog(
                showDialog = showResult,
                correctAnswers = correct,
                passed = passed,
                onDismiss = {
                    showResult = false
                    navController.navigate("caccausai")
                }
            )
        }
    }
}

@Composable
fun ThiSatHachScreenFromFirestore(
    deId: String,
    onSubmit: (
        correctCount: Int,
        passed: Boolean,
        cauSaiList: List<Pair<Question, String>>
    ) -> Unit
) {
    val viewModel: DeThiViewModel = viewModel()
    val deThi by viewModel.deThi.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(deId) {
        viewModel.loadDeThi(deId, soCauCanLay = 35)
    }

    when {
        loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        error != null -> {
            Text(
                text = "Lỗi: $error",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
        deThi.isEmpty() -> {
            Text(
                text = "Không có đề thi",
                modifier = Modifier.padding(16.dp)
            )
        }
        else -> {
            ThiSatHachScreen(deThi = deThi, onSubmit = onSubmit)
        }
    }
}
