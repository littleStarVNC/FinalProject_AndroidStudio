package com.example.finalproject.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LyThuyetScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        FeatureTopBar(title = "Học lý thuyết") {
            navController.popBackStack()
        }
        // Nội dung màn hình
        Text("Đây là màn hình Học lý thuyết", modifier = Modifier.padding(16.dp))
    }
}
