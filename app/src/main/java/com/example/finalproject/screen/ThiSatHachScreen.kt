package com.example.finalproject.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import com.example.finalproject.model.Question

@Composable
fun ThiSatHachScreen(
    deThi: List<Question>,
    onSubmit: (
        correctCount: Int,
        passed: Boolean,
        cauSaiList: List<Pair<Question, String>>
    ) -> Unit
) {
    if (deThi.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Không có câu hỏi", fontSize = 18.sp)
        }
        return
    }

    var currentIndex by remember { mutableStateOf(0) }
    val answers = remember { mutableStateMapOf<Int, String>() }

    // Chặn currentIndex vượt giới hạn
    currentIndex = currentIndex.coerceIn(0, deThi.lastIndex)
    val currentQuestion = deThi.getOrNull(currentIndex) ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Câu ${currentIndex + 1} / ${deThi.size}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Question Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = currentQuestion.noiDung,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Options
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            currentQuestion.options.forEach { option ->
                val selected = answers[currentIndex] == option
                OptionItem(
                    text = option,
                    selected = selected,
                    onClick = { answers[currentIndex] = option }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { currentIndex-- },
                enabled = currentIndex > 0
            ) {
                Text("← Quay lại")
            }

            if (currentIndex < deThi.lastIndex) {
                Button(onClick = { currentIndex++ }) {
                    Text("Tiếp theo →")
                }
            } else {
                Button(onClick = {
                    val correct = answers.count { (index, ans) ->
                        val correctAns = deThi[index].options.getOrNull(
                            deThi[index].correctAnswer.toIntOrNull() ?: -1
                        )
                        ans == correctAns
                    }

                    val diemLietFail = deThi.any { question ->
                        question.isDiemLiet &&
                                answers.getOrDefault(deThi.indexOf(question), "") !=
                                question.options.getOrNull(question.correctAnswer.toIntOrNull() ?: -1)
                    }

                    val cauSaiList = deThi.mapIndexedNotNull { index, question ->
                        val userAns = answers[index]
                        val correctAns = question.options.getOrNull(
                            question.correctAnswer.toIntOrNull() ?: -1
                        )
                        if (userAns != correctAns) question to (userAns ?: "") else null
                    }

                    val passed = !diemLietFail && correct >= 22
                    onSubmit(correct, passed, cauSaiList)
                }) {
                    Text("✅ Nộp bài")
                }
            }
        }
    }
}

@Composable
fun OptionItem(text: String, selected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            else
                Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp
        )
    }
}
