package com.example.finalproject.model

import kotlinx.serialization.Serializable

@Serializable
sealed class ImageSource {
    @Serializable data class Local(val resId: Int) : ImageSource()
    @Serializable data class Url(val url: String) : ImageSource()
}