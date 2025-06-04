package com.example.finalproject.model

data class  Question(
    val id: String = "",
    val noiDung: String = "",
    val options: List<String> = emptyList(),
    val correctAnswer: String = "",
    val isDiemLiet: Boolean = false,
    val isLethalQuestion: Boolean = false
)
