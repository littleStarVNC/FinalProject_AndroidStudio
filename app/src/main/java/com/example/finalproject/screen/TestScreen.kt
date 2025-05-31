package com.example.finalproject.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Question(
    val id: String,
    val text: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String = "",
    val isLethalQuestion: Boolean = false,
    val imageUrl: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(
    navController: NavController,
    categoryId: String,
    categoryTitle: String
) {
    val questions = remember { getMockQuestions(categoryId) }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf(-1) }
    var showAnswer by remember { mutableStateOf(false) }
    var correctAnswers by remember { mutableStateOf(0) }

    val currentQuestion = questions[currentQuestionIndex]

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryTitle) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Progress indicator
            LinearProgressIndicator(
                progress = { (currentQuestionIndex + 1).toFloat() / questions.size },
                modifier = Modifier.fillMaxWidth(),
            )

            Text(
                text = "Câu hỏi ${currentQuestionIndex + 1}/${questions.size}",
                modifier = Modifier.align(Alignment.End).padding(top = 4.dp),
                style = MaterialTheme.typography.bodySmall
            )

            if (currentQuestion.isLethalQuestion) {
                Text(
                    text = "Câu điểm liệt",
                    color = Color.Red,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Question text
            Text(
                text = currentQuestion.text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Question image if available
            currentQuestion.imageUrl?.let { url ->
                // You would implement image loading here
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Image placeholder: $url")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Answer options
            currentQuestion.options.forEachIndexed { index, option ->
                val isSelected = selectedOption == index
                val isCorrect = index == currentQuestion.correctAnswer
                val backgroundColor = when {
                    !showAnswer -> if (isSelected) Color(0xFFE3F2FD) else Color.Transparent
                    isCorrect -> Color(0xFFDCEDC8) // Green for correct
                    isSelected -> Color(0xFFFFCDD2) // Red for selected wrong answer
                    else -> Color.Transparent
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable(enabled = !showAnswer) {
                            selectedOption = index
                        },
                    colors = CardDefaults.cardColors(containerColor = backgroundColor),
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${index + 1}.",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(text = option)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Explanation when answer is shown
            AnimatedVisibility(visible = showAnswer) {
                Column {
                    Text(
                        text = "Giải thích:",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text(text = currentQuestion.explanation)

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Bottom buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (currentQuestionIndex > 0) {
                            currentQuestionIndex--
                            selectedOption = -1
                            showAnswer = false
                        }
                    },
                    enabled = currentQuestionIndex > 0
                ) {
                    Text("Quay lại")
                }

                if (!showAnswer) {
                    Button(
                        onClick = {
                            showAnswer = true
                            if (selectedOption == currentQuestion.correctAnswer) {
                                correctAnswers++
                            }
                        },
                        enabled = selectedOption >= 0
                    ) {
                        Text("Kiểm tra")
                    }
                } else {
                    Button(
                        onClick = {
                            if (currentQuestionIndex < questions.size - 1) {
                                currentQuestionIndex++
                                selectedOption = -1
                                showAnswer = false
                            } else {
                                // Navigate to result screen or show result dialog
                                // For now we'll just show a toast
                                // You'd implement a proper result screen in a real app
                                // Toast.makeText(context, "Kết quả: $correctAnswers/${questions.size}", Toast.LENGTH_LONG).show()
                            }
                        }
                    ) {
                        Text(if (currentQuestionIndex < questions.size - 1) "Câu tiếp theo" else "Kết thúc")
                    }
                }
            }
        }
    }
}

// Mock data function for testing
fun getMockQuestions(categoryId: String): List<Question> {
    return when (categoryId) {
        "concept_rules" -> listOf(
            Question(
                id = "cr1",
                text = "Khái niệm \"đường bộ\" được hiểu như thế nào là đúng?",
                options = listOf(
                    "Đường bộ là đường dành cho xe cơ giới đi lại.",
                    "Đường bộ là công trình công cộng gồm toàn bộ đường, cầu, hầm, bến phà và các công trình phụ trợ khác trên đường để xe cộ qua lại.",
                    "Đường bộ là công trình công cộng gồm lòng đường và hè phố.",
                    "Tất cả các ý trên."
                ),
                correctAnswer = 1,
                explanation = "Theo Luật Giao thông đường bộ, Đường bộ là công trình công cộng gồm toàn bộ đường, cầu, hầm, bến phà và các công trình phụ trợ khác trên đường để xe cộ qua lại."
            ),
            Question(
                id = "cr2",
                text = "Khi điều khiển phương tiện giao thông trên đường bộ, người lái xe phải làm gì?",
                options = listOf(
                    "Có đủ giấy phép lái xe phù hợp với loại xe đang điều khiển.",
                    "Chấp hành quy tắc giao thông đường bộ.",
                    "Có đủ sức khỏe theo quy định.",
                    "Tất cả các ý trên."
                ),
                correctAnswer = 3,
                explanation = "Người lái xe phải đảm bảo tất cả các điều kiện: có giấy phép lái xe phù hợp, chấp hành quy tắc giao thông, và có đủ sức khỏe theo quy định.",
                isLethalQuestion = true
            ),
            Question(
                id = "cr3",
                text = "Tốc độ tối đa cho phép đối với xe máy trong khu dân cư là bao nhiêu?",
                options = listOf(
                    "50 km/h",
                    "40 km/h",
                    "60 km/h",
                    "70 km/h"
                ),
                correctAnswer = 1,
                explanation = "Theo quy định, tốc độ tối đa cho phép đối với xe máy trong khu dân cư là 40 km/h."
            )
        )
        "culture_ethics" -> listOf(
            Question(
                id = "ce1",
                text = "Người lái xe cần có thái độ như thế nào khi tham gia giao thông?",
                options = listOf(
                    "Văn minh, lịch sự, tôn trọng người khác.",
                    "Ưu tiên xe mình trước các phương tiện khác.",
                    "Đi nhanh để tiết kiệm thời gian.",
                    "Tranh thủ vượt đèn đỏ khi không có cảnh sát giao thông."
                ),
                correctAnswer = 0,
                explanation = "Người lái xe cần có thái độ văn minh, lịch sự và tôn trọng người khác khi tham gia giao thông để đảm bảo an toàn và trật tự."
            ),
            Question(
                id = "ce2",
                text = "Khi gặp xe ưu tiên đang làm nhiệm vụ, người lái xe phải làm gì?",
                options = listOf(
                    "Không nhường đường nếu đang có quyền ưu tiên.",
                    "Giảm tốc độ và tấp vào lề đường bên phải để nhường đường.",
                    "Bấm còi liên tục để thông báo cho xe ưu tiên biết.",
                    "Tăng tốc để nhanh chóng rời khỏi khu vực có xe ưu tiên."
                ),
                correctAnswer = 1,
                explanation = "Khi gặp xe ưu tiên đang làm nhiệm vụ, người lái xe phải giảm tốc độ và tấp vào lề đường bên phải để nhường đường theo quy định của Luật Giao thông đường bộ."
            )
        )
        else -> emptyList<Question>()
    }
}