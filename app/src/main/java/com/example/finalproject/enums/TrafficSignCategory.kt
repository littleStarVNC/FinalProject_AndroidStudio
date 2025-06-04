package com.example.finalproject.enums

enum class TrafficSignCategory(val title: String, val type: String) {
    CAM("Biển báo cấm", "cam"),
    HIEULENH("Biển hiệu lệnh", "hieulenh"),
    CHIDAN("Biển chỉ dẫn", "chidan"),
    NGUYHIEM("Biển báo nguy hiểm và cảnh báo", "canhbao"),
    PHU("Biển phụ", "bienphu")
}
