package com.example.finalproject.viewmodel

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.finalproject.model.ImageSource
import com.example.finalproject.model.TrafficNews
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class TrafficNewsViewModel : ViewModel() {
    private val _newsArticles = MutableStateFlow<List<TrafficNews>>(listOf(
        TrafficNews(
            id = 1,
            title = "Tắc đường nghiêm trọng trên M6 do sửa chữa khẩn cấp",
            summary = "Làn đường bị đóng trên M6 hướng bắc từ J28 đến J29 gây tắc nghẽn 30 phút ở Lancashire.",
            publishedDate = "03/06/2025",
            imageRes = ImageSource.Url("https://c8.alamy.com/comp/B9FPHG/england-m6-motorway-view-from-car-in-static-traffic-B9FPHG.jpg"),
            content = "Làn đường trên M6 hướng bắc từ J28 đến J29 đã bị đóng để sửa chữa khẩn cấp, gây ra ùn tắc nghiêm trọng kéo dài hơn 30 phút. Các tài xế được khuyến cáo tìm lộ trình thay thế để tránh khu vực này. Công việc sửa chữa dự kiến kéo dài đến cuối ngày."
        ),
        TrafficNews(
            id = 2,
            title = "Tai nạn trên A380 gần Exeter gây ùn tắc",
            summary = "Va chạm giữa hai phương tiện trên A380 hướng nam gần Kennford khiến giao thông ùn tắc nhiều km.",
            publishedDate = "02/06/2025",
            imageRes = ImageSource.Url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMOVdqmY3RPMO2QE_aDTDv3wBUJmZT_GTtEw&s"),
            content = "Một vụ va chạm giữa hai phương tiện trên A380 hướng nam gần Kennford đã gây ra ùn tắc giao thông nghiêm trọng, với hàng km xe cộ xếp hàng dài. Cảnh sát và đội cứu hộ đã có mặt tại hiện trường để xử lý vụ việc. Người lái xe được khuyến nghị sử dụng các tuyến đường khác."
        ),
        TrafficNews(
            id = 3,
            title = "Số ca tử vong do tai nạn giao thông giảm ở Greenville",
            summary = "Số ca tử vong liên quan đến giao thông tại Greenville giảm từ 41 xuống 16 từ đầu năm 2025.",
            publishedDate = "04/06/2025",
            imageRes = ImageSource.Url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRr10uDmXJ_msA5BJBvcN67sM3CBnSExwUYPg&s"),
            content = "Theo báo cáo mới nhất từ cơ quan quản lý giao thông Greenville, số ca tử vong do tai nạn giao thông đã giảm đáng kể từ 41 xuống còn 16 trong năm 2025. Sự cải thiện này nhờ vào các chiến dịch nâng cao ý thức an toàn giao thông và cải thiện cơ sở hạ tầng đường bộ."
        )
    ))
    val newsArticles: StateFlow<List<TrafficNews>> = _newsArticles.asStateFlow()
}