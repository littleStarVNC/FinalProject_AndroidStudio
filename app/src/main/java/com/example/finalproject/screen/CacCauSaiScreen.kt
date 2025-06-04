package com.example.finalproject.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finalproject.model.Question

@Composable
fun CacCauSaiScreen(
    navController: NavController,
    cauSaiList: List<Pair<Question, String>> // mỗi phần tử gồm: câu hỏi, câu trả lời của người dùng
) {
    Column(modifier = Modifier.fillMaxSize()) {
        FeatureTopBar(title = "Các câu sai") {
            navController.navigate("home") {
                popUpTo("home") { inclusive = true }
            }
        }

        if (cauSaiList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Bạn không sai câu nào!", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cauSaiList) { (question, userAnswer) ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Câu hỏi:", style = MaterialTheme.typography.labelLarge)
                            Text(question.noiDung, style = MaterialTheme.typography.bodyLarge)

                            Spacer(modifier = Modifier.height(8.dp))

                            Text("Lựa chọn của bạn:", color = MaterialTheme.colorScheme.error)
                            Text(userAnswer, style = MaterialTheme.typography.bodyMedium)

                            Spacer(modifier = Modifier.height(4.dp))

                            val correctIndex = question.correctAnswer.toIntOrNull()
                            val correctText = correctIndex?.let {
                                question.options.getOrNull(it) ?: "Không rõ"
                            } ?: "Không rõ"

                            Text("Đáp án đúng:", color = MaterialTheme.colorScheme.primary)
                            Text(correctText, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
