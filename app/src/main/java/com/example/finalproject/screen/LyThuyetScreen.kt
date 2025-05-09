package com.example.finalproject.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.finalproject.screens.TopBar
import androidx.compose.ui.Modifier  // Thêm dòng này để tránh lỗi Unresolved reference: fillMaxSize

@Composable
fun LyThuyetScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Thanh Top Bar với nút Back
        TopBar(navController = navController, title = "Học lý thuyết")

        // Nội dung của màn hình
        Text(text = "Màn hình Học lý thuyết")
    }
}
