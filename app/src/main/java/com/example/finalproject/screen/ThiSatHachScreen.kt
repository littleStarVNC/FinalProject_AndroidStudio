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
fun ThiSatHachScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        FeatureTopBar(title = "Thi sát hạch") {
            navController.popBackStack()
        }
        // Nội dung màn hình
        Text("Đây là màn hình Thi sát hạch", modifier = Modifier.padding(16.dp))
    }
}
