package com.example.finalproject.util

import com.example.finalproject.model.Question

fun getDeThi(soDe: Int): List<Question> {
    return listOf(
        Question("Câu 1: Đèn đỏ có được đi không?", listOf("Không", "Có", "Tùy", "Không chắc"), 0, isDiemLiet = false),
        Question("Câu 2: Gặp biển cấm mà vẫn đi tiếp là?", listOf("Sai", "Đúng", "Bình thường", "Hên xui"), 0, isDiemLiet = true),
        // Bạn thêm các câu khác tuỳ đề...
    )
}
