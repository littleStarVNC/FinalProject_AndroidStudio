package com.example.finalproject.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Serializable
data class TrafficNews(
    val id: Int,
    val title: String,
    val summary: String,
    val publishedDate: String,
    @Contextual val imageRes: ImageSource,
    val content: String
)