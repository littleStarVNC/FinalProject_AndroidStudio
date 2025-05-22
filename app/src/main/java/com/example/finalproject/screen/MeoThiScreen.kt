package com.example.finalproject.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MeoThiScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        FeatureTopBar(title = "Mẹo Thi") {
            navController.popBackStack()
        }
        // Nội dung màn hình
        Text("Đây là màn hình Mẹo thi", modifier = Modifier.padding(16.dp))
    }
}
