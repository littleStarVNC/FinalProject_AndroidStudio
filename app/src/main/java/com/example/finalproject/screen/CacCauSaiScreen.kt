package com.example.finalproject.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CacCauSaiScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        FeatureTopBar(title = "Các câu sai") {
            navController.popBackStack()
        }
        // Nội dung màn hình
        Text("Đây là màn hình Các câu sai", modifier = Modifier.padding(16.dp))
    }
}
