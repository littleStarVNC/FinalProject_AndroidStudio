package com.example.finalproject.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finalproject.R
import com.example.finalproject.ui.theme.TrafficSign
import com.example.finalproject.ui.theme.trafficSigns

@Composable
fun BienBaoScreen(navController: NavController) {
    var currentTab by remember { mutableStateOf(0) }
    val tabs = listOf("Học Biển Báo", "Kiểm Tra Kiến Thức")

    Column(modifier = Modifier.fillMaxSize()) {
        FeatureTopBar(title = "Biển Báo Giao Thông") {
            navController.popBackStack()
        }

        TabRow(selectedTabIndex = currentTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = currentTab == index,
                    onClick = { currentTab = index },
                    text = { Text(title) }
                )
            }
        }

        when (currentTab) {
            0 -> LearnTrafficSignsContent()
            1 -> TrafficSignsQuizContent()
        }
    }
}

@Composable
fun LearnTrafficSignsContent() {
    var selectedCategory by remember { mutableStateOf("Tất cả") }
    val categories = listOf("Tất cả", "Biển Báo Cấm", "Biển Báo Hiệu Lệnh")

    // Filter the traffic signs based on selected category
    val filteredSigns = trafficSigns.filter { sign ->
        selectedCategory == "Tất cả" || sign.type.equals(
            when(selectedCategory) {
                "Biển Báo Cấm" -> "cam"
                "Biển Báo Hiệu Lệnh" -> "hieulenh"
                else -> "Biển báo chỉ dẫn"
            },
            ignoreCase = true
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Chọn loại biển báo:", modifier = Modifier.padding(bottom = 8.dp))

        // Improved filter chips row with proper horizontal arrangement
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            categories.forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category) },
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

        // Corrected modifier order for LazyColumn
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(filteredSigns.size) { index ->
                TrafficSignItem(filteredSigns[index])
            }
        }
    }
}
@Composable
fun TrafficSignItem(sign: TrafficSign) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = sign.imageRes),
                    contentDescription = sign.title,
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = sign.title,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = sign.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun TrafficSignsQuizContent() {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var answered by remember { mutableStateOf(false) }
    var selectedAnswer by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Progress indicator
        LinearProgressIndicator(
            progress = (currentQuestionIndex.toFloat() + 1) / trafficSignQuiz.size,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Display current question or results
        if (currentQuestionIndex < trafficSignQuiz.size) {
            QuestionCard(
                question = trafficSignQuiz[currentQuestionIndex],
                selectedAnswer = selectedAnswer,
                answered = answered,
                onAnswerSelected = { index ->
                    if (!answered) {
                        selectedAnswer = index
                        answered = true
                        if (index == trafficSignQuiz[currentQuestionIndex].correctAnswerIndex) {
                            score++
                        }
                    }
                },
                onNextQuestion = {
                    currentQuestionIndex++
                    answered = false
                    selectedAnswer = -1
                }
            )
        } else {
            // Show quiz results
            ResultCard(
                score = score,
                totalQuestions = trafficSignQuiz.size,
                onRestartQuiz = {
                    currentQuestionIndex = 0
                    score = 0
                    answered = false
                    selectedAnswer = -1
                }
            )
        }
    }
}

@Composable
fun QuestionCard(
    question: TrafficSignQuestion,
    selectedAnswer: Int,
    answered: Boolean,
    onAnswerSelected: (Int) -> Unit,
    onNextQuestion: () -> Unit
) {
    val primary = MaterialTheme.colorScheme.primary
    val onPrimary = MaterialTheme.colorScheme.onPrimary
    val surface = MaterialTheme.colorScheme.surface
    val onSurface = MaterialTheme.colorScheme.onSurface
    val correctColor = MaterialTheme.colorScheme.tertiaryContainer
    val onCorrectColor = MaterialTheme.colorScheme.onTertiaryContainer
    val errorColor = MaterialTheme.colorScheme.errorContainer
    val onErrorColor = MaterialTheme.colorScheme.onErrorContainer

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Question image
            Image(
                painter = painterResource(id = question.imageRes),
                contentDescription = "Question Image",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            // Question text
            Text(
                text = question.question,
                style = MaterialTheme.typography.titleMedium,
                color = onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Answer choices
            question.options.forEachIndexed { index, option ->
                val isSelected = selectedAnswer == index
                val isCorrect = index == question.correctAnswerIndex
                val (backgroundColor, contentColor) = when {
                    !answered && isSelected -> primary to onPrimary
                    !answered -> surface to onSurface
                    isCorrect -> correctColor to onCorrectColor
                    isSelected -> errorColor to onErrorColor
                    else -> surface to onSurface
                }

                Button(
                    onClick = { onAnswerSelected(index) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = backgroundColor,
                        contentColor = contentColor
                    ),
                    enabled = !answered
                ) {
                    Text(option)
                }
            }

            // Next button
            if (answered) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onNextQuestion,
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primary,
                        contentColor = onPrimary
                    )
                ) {
                    Text("Tiếp Tục")
                }
            }
        }
    }
}

@Composable
fun ResultCard(score: Int, totalQuestions: Int, onRestartQuiz: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Kết Quả",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Bạn đã trả lời đúng $score/$totalQuestions câu hỏi!",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Button(onClick = onRestartQuiz) {
                Text("Làm Lại Bài Kiểm Tra")
            }
        }
    }
}

// Data models for the quiz
data class TrafficSignQuestion(
    val imageRes: Int,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

val trafficSignQuiz = listOf(
    TrafficSignQuestion(
        imageRes = R.drawable.camdinguocchieu,
        question = "Biển báo này có ý nghĩa gì?",
        options = listOf(
            "Cấm đi ngược chiều",
            "Đường cấm",
            "Cấm dừng xe",
            "Cấm đỗ xe"
        ),
        correctAnswerIndex = 0
    ),
    TrafficSignQuestion(
        imageRes = R.drawable.camoto,
        question = "Biển báo này có ý nghĩa gì?",
        options = listOf(
            "Cấm xe ô tô",
            "Cấm xe máy",
            "Cấm tất cả các loại xe",
            "Cấm xe tải"
        ),
        correctAnswerIndex = 0
    ),
    TrafficSignQuestion(
        imageRes = R.drawable.camotorephai,
        question = "Biển báo này cấm làm gì?",
        options = listOf(
            "Cấm rẽ phải",
            "Cấm rẽ trái",
            "Cấm đi thẳng",
            "Cấm quay đầu"
        ),
        correctAnswerIndex = 0
    ),
    TrafficSignQuestion(
        imageRes = R.drawable.camotoretrai,
        question = "Biển báo này có ý nghĩa gì?",
        options = listOf(
            "Cấm rẽ phải",
            "Cấm rẽ trái",
            "Cấm đi thẳng",
            "Cấm quay đầu"
        ),
        correctAnswerIndex = 1
    ),
    TrafficSignQuestion(
        imageRes = R.drawable.camquaydau,
        question = "Biển báo này có ý nghĩa gì?",
        options = listOf(
            "Cấm đi ngược chiều",
            "Cấm quay đầu xe",
            "Cấm rẽ trái",
            "Cấm dừng và đỗ"
        ),
        correctAnswerIndex = 1
    ),
    TrafficSignQuestion(
        imageRes = R.drawable.camxemay,
        question = "Biển báo này có ý nghĩa gì?",
        options = listOf(
            "Cấm xe đạp",
            "Cấm xe máy",
            "Cấm người đi bộ",
            "Cấm xe ba bánh"
        ),
        correctAnswerIndex = 1
    ),
    TrafficSignQuestion(
        imageRes = R.drawable.camxeotovaxemay,
        question = "Biển báo này có ý nghĩa gì?",
        options = listOf(
            "Cấm xe ô tô",
            "Cấm xe máy",
            "Cấm xe ô tô và xe máy",
            "Cấm tất cả các phương tiện"
        ),
        correctAnswerIndex = 2
    ),
    TrafficSignQuestion(
        imageRes = R.drawable.huongphaiditheophai,
        question = "Biển báo xanh với mũi tên trắng chỉ sang phải có ý nghĩa gì?",
        options = listOf(
            "Được phép rẽ phải",
            "Hướng đi bắt buộc phải rẽ phải",
            "Đường một chiều rẽ phải",
            "Đường có nhiều làn đường"
        ),
        correctAnswerIndex = 1
    ),
    TrafficSignQuestion(
        imageRes = R.drawable.huongphaiditheotrai,
        question = "Biển báo xanh với mũi tên trắng chỉ sang trái có ý nghĩa gì?",
        options = listOf(
            "Được phép rẽ trái",
            "Hướng đi bắt buộc phải rẽ trái",
            "Đường một chiều rẽ trái",
            "Đường có nhiều làn đường"
        ),
        correctAnswerIndex = 1
    ),
    TrafficSignQuestion(
        imageRes = R.drawable.stop,
        question = "Biển báo dạng bát giác màu đỏ có chữ STOP có ý nghĩa gì?",
        options = listOf(
            "Cấm dừng xe",
            "Cấm đỗ xe",
            "Bắt buộc dừng xe",
            "Giao nhau với đường ưu tiên"
        ),
        correctAnswerIndex = 2
    )
)