package com.example.finalproject.repository


import com.example.finalproject.R
import com.example.finalproject.model.TrafficSign
import com.example.finalproject.enums.TrafficSignType
import com.example.finalproject.enums.Difficulty

class TrafficSignRepository {
    private val trafficSigns = listOf(
        TrafficSign(
            id = 1,
            imageRes = R.drawable.camdinguocchieu,
            title = "Cấm đi ngược chiều",
            type = TrafficSignType.PROHIBITION,
            description = "Biển báo này cấm các phương tiện đi ngược chiều đường quy định",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 2,
            imageRes = R.drawable.camxemay,
            title = "Cấm xe máy",
            type = TrafficSignType.PROHIBITION,
            description = "Biển báo cấm xe mô tô, xe gắn máy đi qua",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 3,
            imageRes = R.drawable.huongphaiditheophai,
            title = "Hướng phải đi theo phải",
            type = TrafficSignType.MANDATORY,
            description = "Các phương tiện chỉ được phép rẽ phải",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 4,
            imageRes = R.drawable.huongphaiditheotrai,
            title = "Hướng phải đi theo trái",
            type = TrafficSignType.MANDATORY,
            description = "Các phương tiện chỉ được phép rẽ trái",
            difficulty = Difficulty.MEDIUM
        )
    )

    fun getAllTrafficSigns(): List<TrafficSign> = trafficSigns

    fun getTrafficSignsByType(type: TrafficSignType): List<TrafficSign> {
        return if (type == TrafficSignType.ALL) {
            trafficSigns
        } else {
            trafficSigns.filter { it.type == type }
        }
    }

    fun getTrafficSignById(id: Int): TrafficSign? {
        return trafficSigns.find { it.id == id }
    }

    fun searchTrafficSigns(query: String): List<TrafficSign> {
        return trafficSigns.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.description?.contains(query, ignoreCase = true) == true
        }
    }
}