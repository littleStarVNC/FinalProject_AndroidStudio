package com.example.finalproject.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.finalproject.model.TrafficSign
import com.example.finalproject.repository.TrafficSignRepository
import com.example.finalproject.enums.TrafficSignType
import com.example.finalproject.model.ImageSource
import com.example.finalproject.model.QuizQuestion
import com.example.finalproject.viewmodel.TrafficSignViewModel
import com.example.finalproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BienBaoScreen(
    navController: NavController,
    viewModel: TrafficSignViewModel = viewModel()
) {
    var currentTab by remember { mutableStateOf(0) }
    val tabs = listOf("Học Biển Báo", "Kiểm Tra Kiến Thức", "Yêu Thích")

    // Tạo câu hỏi quiz mẫu
    val sampleQuiz = QuizQuestion(
        id = 1,
        signImageRes = ImageSource.Local(R.drawable.camdinguocchieu),
        question = "Biển báo này có ý nghĩa gì?",
        options = listOf("Cấm đi ngược chiều", "Đường ưu tiên", "Đường cấm", "Hướng đi phải"),
        correctAnswer = 0,
        explanation = "Đây là biển báo cấm đi ngược chiều, người tham gia giao thông không được đi vào đường này."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFFBBDEFB), Color(0xFFE1BEE7))
                )
            )
    ) {
        FeatureTopBar(title = "Biển Báo Giao Thông") {
            navController.popBackStack()
        }

        TabRow(
            selectedTabIndex = currentTab,
            containerColor = Color(0xFF64B5F6)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = currentTab == index,
                    onClick = { currentTab = index },
                    text = { Text(title, fontWeight = FontWeight.Bold, color = Color.White) }
                )
            }
        }

        when (currentTab) {
            0 -> LearnTrafficSignsContent(viewModel)
            1 -> TrafficSignsQuizContent(viewModel)
            2 -> FavoriteSignsContent(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnTrafficSignsContent(viewModel: TrafficSignViewModel) {
    val categories = listOf(
        TrafficSignType.ALL,
        TrafficSignType.PROHIBITION,
        TrafficSignType.MANDATORY,
        TrafficSignType.WARNING,
        TrafficSignType.INFORMATION
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = viewModel.searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            label = { Text("Tìm kiếm biển báo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(Color.White, RoundedCornerShape(12.dp)),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1976D2),
                unfocusedBorderColor = Color(0xFF90CAF9)
            )
        )

        Text(
            "Chọn loại biển báo",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0288D1),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            modifier = Modifier.padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                FilterChip(
                    selected = viewModel.selectedCategory == category,
                    onClick = { viewModel.updateCategory(category) },
                    label = { Text(category.displayName) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF42A5F5),
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        Text(
            "Tìm thấy ${viewModel.filteredSigns.size} biển báo",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF0288D1),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.filteredSigns) { sign ->
                EnhancedTrafficSignItem(
                    sign = sign,
                    isFavorite = viewModel.isFavorite(sign.id),
                    onFavoriteClick = { viewModel.toggleFavorite(sign.id) }
                )
            }
        }
    }
}

@Composable
fun EnhancedTrafficSignItem(
    sign: TrafficSign,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (expanded) 1.05f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable { expanded = !expanded }
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB))
                )
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = when (val img = sign.imageRes) {
                        is ImageSource.Local -> painterResource(id = img.resId)
                        is ImageSource.Url -> rememberAsyncImagePainter(model = img.url)
                    },
                    contentDescription = sign.title,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF5F5F5))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = sign.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color(0xFF01579B)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row {
                        AssistChip(
                            onClick = { },
                            label = {
                                Text(
                                    text = sign.type.displayName,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color(0xFF0277BD)
                                )
                            },
                            modifier = Modifier.padding(end = 8.dp),
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = Color(0xFFB3E5FC)
                            )
                        )

                        AssistChip(
                            onClick = { },
                            label = {
                                Text(
                                    text = sign.difficulty.displayName,
                                    fontSize = 14.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color(0xFF0277BD)
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = Color(0xFFB3E5FC)
                            )
                        )
                    }
                }

                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite) "Bỏ yêu thích" else "Yêu thích",
                        tint = if (isFavorite) Color(0xFFF06292) else Color(0xFF0288D1)
                    )
                }
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Color(0xFF90CAF9))
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Mô tả",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF01579B)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = sign.description ?: "Không có mô tả",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF0288D1)
                )
            }
        }
    }
}

@Composable
fun FavoriteSignsContent(viewModel: TrafficSignViewModel) {
    val favoriteSignIds = viewModel.favoriteSignIds
    val allSigns = TrafficSignRepository().getAllTrafficSigns()
    val favoriteSigns = allSigns.filter { favoriteSignIds.contains(it.id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFE1F5FE))
    ) {
        Text(
            "Biển báo yêu thích",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD81B60),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (favoriteSigns.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Chưa có biển báo yêu thích nào",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF0288D1)
                )
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(favoriteSigns) { sign ->
                    EnhancedTrafficSignItem(
                        sign = sign,
                        isFavorite = true,
                        onFavoriteClick = { viewModel.toggleFavorite(sign.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun TrafficSignsQuizContent(viewModel: TrafficSignViewModel = viewModel()) {
    // Lấy danh sách câu hỏi từ ViewModel
    val quizQuestions by viewModel.quizQuestions.collectAsState()
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswered by remember { mutableStateOf(false) }
    val score by viewModel.score

    // Xử lý trường hợp không có câu hỏi
    if (quizQuestions.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Không có câu hỏi nào",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hiển thị điểm số
            Text(
                text = "Điểm: $score",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = quizQuestions[currentQuestionIndex].question,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = when (val img = quizQuestions[currentQuestionIndex].signImageRes) {
                    is ImageSource.Local -> painterResource(id = img.resId)
                    is ImageSource.Url -> rememberAsyncImagePainter(model = img.url)
                },
                contentDescription = quizQuestions[currentQuestionIndex].question,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            quizQuestions[currentQuestionIndex].options.forEachIndexed { index, option ->
                Button(
                    onClick = {
                        if (!isAnswered) {
                            selectedOptionIndex = index
                            isAnswered = true
                            if (index == quizQuestions[currentQuestionIndex].correctAnswer) {
                                viewModel.updateScore(score + 10)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when {
                            !isAnswered -> MaterialTheme.colorScheme.primary
                            index == quizQuestions[currentQuestionIndex].correctAnswer -> Color.Green
                            index == selectedOptionIndex -> MaterialTheme.colorScheme.error
                            else -> MaterialTheme.colorScheme.primary
                        }
                    ),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !isAnswered
                ) {
                    Text(option)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isAnswered) {
                val isCorrect = selectedOptionIndex == quizQuestions[currentQuestionIndex].correctAnswer
                Text(
                    text = if (isCorrect) "Đúng" else "Sai",
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (isCorrect) Color.Green else MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = quizQuestions[currentQuestionIndex].explanation,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        isAnswered = false
                        selectedOptionIndex = null
                        currentQuestionIndex = (currentQuestionIndex + 1) % quizQuestions.size
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Câu hỏi tiếp theo")
                }
            }
        }
    }
}