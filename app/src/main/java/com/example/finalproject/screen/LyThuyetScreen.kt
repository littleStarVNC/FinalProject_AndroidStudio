package com.example.finalproject.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LyThuyetScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Học Lý Thuyết") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Hành động giỏ hoặc xóa */ }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            CategoryItem(
                icon = Icons.Default.Description,
                title = "KHÁI NIỆM VÀ QUY TẮC",
                subTitle = "Gồm 83 câu hỏi",
                dangerInfo = "(18 Câu điểm liệt)",
                progress = 0,
                total = 83
            )
            Spacer(modifier = Modifier.height(12.dp))

            CategoryItem(
                icon = Icons.Default.Group,
                title = "VĂN HOÁ VÀ ĐẠO ĐỨC LÁI",
                subTitle = "Gồm 5 câu hỏi",
                progress = 0,
                total = 5
            )

            // Tiếp tục thêm các mục khác
        }
    }
}
@Composable
fun CategoryItem(
    icon: ImageVector,
    title: String,
    subTitle: String,
    dangerInfo: String? = null,
    progress: Int,
    total: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color(0xFF00BCD4),
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(subTitle, color = Color.Gray, fontSize = 14.sp)
                    if (dangerInfo != null) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(dangerInfo, color = Color(0xFFFF9800), fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                LinearProgressIndicator(
                    progress = progress / total.toFloat(),
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "$progress/$total",
                    modifier = Modifier.align(Alignment.End),
                    fontSize = 12.sp,
                    color = Color(0xFF00BCD4)
                )
            }
        }
    }
}
