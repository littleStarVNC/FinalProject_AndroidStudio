package com.example.finalproject.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun KetQuaDialog(
    showDialog: Boolean,
    correctAnswers: Int,
    passed: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Kết quả thi") },
            text = {
                Column {
                    Text("Bạn đã trả lời đúng $correctAnswers câu.")
                    Spacer(modifier = Modifier.height(8.dp))
                    if (passed) {
                        Text("Chúc mừng! Bạn đã ĐẠT", color = Color.Green)
                    } else {
                        Text("Rất tiếc! Bạn KHÔNG ĐẠT", color = Color.Red)
                    }
                }
            },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("Đóng")
                }
            }
        )
    }
}
