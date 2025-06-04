package com.example.finalproject.model

data class QuizQuestion(
    val id: Int,
    val signImageRes: ImageSource,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String
)