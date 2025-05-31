package com.example.finalproject.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MeoThiScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        FeatureTopBar(title = "Mẹo Thi") {
            navController.popBackStack()
        }

        // Content area
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            // Introduction section
            item {
                Text(
                    text = "Mẹo thi lý thuyết bằng lái xe",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Text(
                    text = "Tổng hợp những mẹo thi lý thuyết giúp bạn dễ dàng vượt qua kỳ thi sát hạch lý thuyết.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Tips categories
            item {
                ExpandableTipsSection(
                    title = "Cấu trúc đề thi",
                    tips = listOf(
                        "Bộ đề thi gồm 200 câu hỏi lý thuyết.",
                        "Mỗi bài thi sát hạch có 25 câu hỏi trong thời gian 19 phút.",
                        "Yêu cầu đạt 21/25 câu đúng để đạt bài thi.",
                        "Bài thi có từ 2-4 câu điểm liệt, làm sai câu điểm liệt sẽ trượt ngay lập tức."
                    )
                )
            }

            item {
                ExpandableTipsSection(
                    title = "Mẹo giải phần khái niệm và quy tắc",
                    tips = listOf(
                        "Những câu có từ \"Bị nghiêm cấm\", \"Không được\" thì chọn đáp án có các từ đó.",
                        "Câu hỏi về \"Phương tiện giao thông cơ giới đường bộ\" chọn đáp án KHÔNG có \"xe cho người khuyết tật\".",
                        "Câu hỏi về \"Phương tiện giao thông thô sơ đường bộ\" chọn đáp án CÓ \"xe cho người khuyết tật\" nhưng KHÔNG có \"xe gắn máy\".",
                        "Câu hỏi về người điều khiển giao thông chọn đáp án có \"Cảnh sát giao thông\".",
                        "Khái niệm \"Dừng xe\": đứng yên tạm thời, giới hạn thời gian. \"Đỗ xe\": không giới hạn thời gian.",
                        "Độ tuổi: 16-18 tuổi lái xe gắn máy dưới 50cm³; 18 tuổi trở lên lái xe mô tô từ 50cm³ trở lên.",
                        "Bằng A1 lái được xe mô tô 2 bánh 50-175cm³ và xe 3 bánh cho người khuyết tật."
                    )
                )
            }

            item {
                ExpandableTipsSection(
                    title = "Mẹo giải phần biển báo",
                    tips = listOf(
                        "Biển báo nguy hiểm: hình tam giác đều, viền đỏ, nền vàng, hình vẽ đen.",
                        "Biển báo cấm: hình tròn, viền đỏ, nền trắng, hình vẽ đen, gạch chéo đỏ.",
                        "Biển hiệu lệnh: hình tròn, nền xanh, hình vẽ trắng.",
                        "Biển chỉ dẫn: hình vuông hoặc chữ nhật, nền xanh, hình vẽ trắng.",
                        "Khi thấy \"Cấm cơ giới\" thì chọn Biển 1.",
                        "Câu hỏi có 2 biển báo chữ nhật màu xanh thì chọn đáp án 1.",
                        "Câu hỏi có 2-3 biển báo hình tròn màu xanh: nếu câu hỏi dài 1 hàng chọn câu 1, dài hơn 1 hàng chọn câu 3."
                    )
                )
            }

            item {
                ExpandableTipsSection(
                    title = "Mẹo giải phần sa hình",
                    tips = listOf(
                        "Thứ tự ưu tiên: Xe vào nơi giao nhau trước → Xe ưu tiên → Xe trên đường ưu tiên → Xe bên phải không vướng → Thứ tự rẽ phải - đi thẳng - rẽ trái.",
                        "Xe ưu tiên: Xe cứu hỏa > Xe công an, quân sự > Xe cứu thương > Xe hộ đê, khắc phục sự cố > Đoàn xe tang.",
                        "Xe con luôn đúng: khi hỏi xe nào đúng, chọn đáp án có xe con; hỏi xe nào sai, chọn đáp án không có xe con.",
                        "Có hình CSGT, vòng tròn hoặc vòng xuyến thì chọn đáp án 3.",
                        "Vòng xuyến có báo hiệu: nhường bên tay trái; không có báo hiệu: nhường bên tay phải.",
                        "Nếu sa hình chỉ có xe, không có biển báo hay đèn, thì đếm số xe và chọn đáp án tương ứng (3 xe chọn đáp án 3).",
                        "Có mũi tên màu đỏ: đếm số ngã - 1 = đáp án (3 ngã chọn đáp án 2)."
                    )
                )
            }

            item {
                ExpandableTipsSection(
                    title = "Quy định tốc độ",
                    tips = listOf(
                        "Trong khu dân cư: tối đa 50km/h (đường đôi hoặc một chiều) hoặc 60km/h (đường hai chiều có dải phân cách).",
                        "Ngoài khu dân cư: đường đôi hoặc một chiều không có dải phân cách tối đa 80km/h, có dải phân cách 90km/h.",
                        "Đường cao tốc: Xe con, xe bán tải tối đa 120km/h; Xe tải, khách đến 30 chỗ tối đa 100km/h.",
                        "Xe mô tô: Trong khu dân cư tối đa 40km/h; ngoài khu dân cư tối đa 70km/h."
                    )
                )
            }

            item {
                ExpandableTipsSection(
                    title = "Lời khuyên thi cử",
                    tips = listOf(
                        "Giữ tinh thần thoải mái, bình tĩnh trước khi vào phòng thi.",
                        "Đến trung tâm thi trước ít nhất 1 tiếng để làm thủ tục.",
                        "Tắt điện thoại hoặc để chế độ im lặng trước khi vào phòng thi.",
                        "Đọc kỹ từng câu hỏi trước khi chọn đáp án.",
                        "Làm xong nên dò lại trước khi bấm kết thúc.",
                        "Đặc biệt lưu ý các câu điểm liệt.",
                        "Nên ôn tập nhiều lần trước ngày thi."
                    )
                )
            }

            // Bottom padding
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ExpandableTipsSection(title: String, tips: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "rotation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    modifier = Modifier.rotate(rotationState)
                )
            }

            // Content
            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                        .padding(16.dp)
                ) {
                    tips.forEach { tip ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "•",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(end = 8.dp, top = 2.dp)
                            )
                            Text(
                                text = tip,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}