package com.example.finalproject.model

import com.example.finalproject.enums.Difficulty
import com.example.finalproject.enums.TrafficSignType

data class TrafficSign(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val type: TrafficSignType,
    val description: String? = null,
    val difficulty: Difficulty = Difficulty.EASY,
    val isFavorite: Boolean = false
)