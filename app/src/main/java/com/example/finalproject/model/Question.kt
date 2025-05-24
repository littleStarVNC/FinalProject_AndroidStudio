package com.example.finalproject.model

data class Question(
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val isDiemLiet: Boolean = false
)