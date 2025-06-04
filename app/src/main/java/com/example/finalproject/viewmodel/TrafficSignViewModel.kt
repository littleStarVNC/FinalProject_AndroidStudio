package com.example.finalproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import com.example.finalproject.repository.TrafficSignRepository
import com.example.finalproject.model.TrafficSign
import com.example.finalproject.enums.TrafficSignType

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