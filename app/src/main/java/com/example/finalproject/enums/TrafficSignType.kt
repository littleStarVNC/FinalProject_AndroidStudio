package com.example.finalproject.enums

enum class TrafficSignType(val displayName: String, val key: String) {
    ALL("Tất cả", "all"),
    PROHIBITION("Biển Báo Cấm", "cam"),
    MANDATORY("Biển Báo Hiệu Lệnh", "hieulenh"),
    WARNING("Biển Báo Nguy Hiểm", "nguyHiem"),
    INFORMATION("Biển Báo Chỉ Dẫn", "chiDan")
}