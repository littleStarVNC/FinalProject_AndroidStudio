package com.example.finalproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import com.example.finalproject.repository.TrafficSignRepository
import com.example.finalproject.model.TrafficSign
import com.example.finalproject.enums.TrafficSignType
import com.example.finalproject.model.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrafficSignViewModel : ViewModel() {
    private val repository = TrafficSignRepository()

    var selectedCategory by mutableStateOf(TrafficSignType.ALL)
        private set

    var searchQuery by mutableStateOf("")
        private set

    var favoriteSignIds by mutableStateOf(setOf<Int>())
        private set

    val filteredSigns: List<TrafficSign>
        get() {
            val signsByCategory = repository.getTrafficSignsByType(selectedCategory)
            return if (searchQuery.isBlank()) {
                signsByCategory
            } else {
                signsByCategory.filter { sign ->
                    sign.title.contains(searchQuery, ignoreCase = true) ||
                            sign.description?.contains(searchQuery, ignoreCase = true) == true
                }
            }
        }
    private val _quizQuestions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val quizQuestions: StateFlow<List<QuizQuestion>> = _quizQuestions.asStateFlow()
    private val _score = mutableStateOf(0)
    val score: State<Int> = _score

    init {
        refreshQuizQuestions() // Tải câu hỏi khi khởi tạo
    }

    fun refreshQuizQuestions() {
        val signs = TrafficSignRepository().getAllTrafficSigns()
        val questions = signs.mapIndexed { index, sign ->
            QuizQuestion(
                id = index + 1,
                signImageRes = sign.imageRes,
                question = "Biển báo ${sign.title} có ý nghĩa gì? 🤔",
                options = generateOptions(sign), // Hàm tạo danh sách lựa chọn ngẫu nhiên
                correctAnswer = 0,
                explanation = sign.description ?: "Không có mô tả."
            )
        }.shuffled() // Xáo trộn để ngẫu nhiên hóa
        _quizQuestions.value = questions
    }

    fun updateScore(newScore: Int) {
        _score.value = newScore
    }

    private fun generateOptions(sign: TrafficSign): List<String> {
        // Logic tạo 4 lựa chọn, trong đó lựa chọn đầu tiên là đúng
        val correctOption = sign.description?.substringBefore(".") ?: sign.title
        val wrongOptions = TrafficSignRepository().getAllTrafficSigns()
            .filter { it != sign }
            .shuffled()
            .take(3)
            .map { it.description?.substringBefore(".") ?: it.title }
        return (listOf(correctOption) + wrongOptions).shuffled()
    }
    fun updateCategory(category: TrafficSignType) {
        selectedCategory = category
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun toggleFavorite(signId: Int) {
        favoriteSignIds = if (favoriteSignIds.contains(signId)) {
            favoriteSignIds - signId
        } else {
            favoriteSignIds + signId
        }
    }

    fun isFavorite(signId: Int): Boolean = favoriteSignIds.contains(signId)
}