package com.example.finalproject.ui.theme

import com.example.finalproject.R

data class TrafficSign(
    val imageRes: Int,
    val title: String,
    val description: String,
    val type: String // ví dụ: "cam", "hieulenh", "chidan"
)
val trafficSigns = listOf(
    TrafficSign(
        imageRes = R.drawable.camquaydau,
        title = "Biển số P.124a - Cấm quay đầu xe",
        description = "Để báo cấm các loại xe quay đầu",
        type = "cam"
    ),
    TrafficSign(
        imageRes = R.drawable.camxeotovaxemay,
        title = "Biển số P105 - Cấm xe ô tô và xe máy",
        description = "Để báo đường cấm các loại xe cơ giới và xe máy đi qua",
        type = "cam"
    ),
    TrafficSign(
        imageRes = R.drawable.duongcam,
        title = "Biển số P.101 - Đường cấm",
        description = "Để báo đường cấm các loại phương tiện đi lại cả hai hướng",
        type = "cam"
    ),
    TrafficSign(
        imageRes = R.drawable.camdinguocchieu,
        title = "Biển số P.102 - Cấm đi ngược chiều",
        description = "Để bảo đường cấm các loại xe (cơ giới và thô sơ) đi vào theo chiều đặt biển, trừ các xe được ưu tiên theo quy định. Người đi bộ được phép đi trên vỉa hè hoặc lề đường",
        type = "cam"
    ),
    TrafficSign(
        imageRes = R.drawable.camoto,
        title = "Biển số P.103a - Cắm xe ô tô",
        description = "Để bảo đường cấm các loại xe cơ giới kể cả xe máy 3 bánh có thùng đi qua, trừ xe máy 2 bánh, xe gắn máy và các xe được ưu tiên theo quy định.",
        type = "cam"
    ),
    TrafficSign(
        imageRes = R.drawable.camotorephai,
        title = "Biển số P.103b - Cắm xe ô tô rẽ phải",
        description = "Cấm xe ô tô rẽ phải.Để bảo đường cấm các loại xe cơ giới kể cả xe máy 3 bánh có thùng rẽ phải, trừ xe máy 2 bánh, xe gắn máy và các xe được ưu tiên theo quy định",
        type = "cam"
    ),
    TrafficSign(
        imageRes = R.drawable.camotoretrai,
        title = "Biển số P.103b Cắm xe ô tô rẽ trái",
        description = "Cấm xe ô tô rẽ trái.Để bảo đường cấm các loại xe cơ giới kể cả xe máy 3 bánh có thùng rẽ trai, trừ xe máy 2 bánh, xe gắn máy và các xe được ưu tiên theo quy định",
        type = "cam"
    ),
    TrafficSign(
        imageRes = R.drawable.camxemay,
        title = "Biển số P.104 \"Cắm xe máy\"",
        description = "Để bảo đường cấm các loại xe máy, trừ xe máy được ưu tiên theo quy định, phải đặt biển số P.104 \"Cắm xe máy\". Biển không có giá trị cấm những người đặt xe máy.",
        type = "cam"
    ),
    TrafficSign(
        imageRes = R.drawable.stop,
        title = "Biển số R.122 \"Dừng lại\"",
        description = "Để bảo các xe (cơ giới và thô sơ) dừng lại.",
        type = "hieulenh"
    ),
    TrafficSign(
        imageRes = R.drawable.huongdiphaitheo,
        title = "Biển số R.301a \"Hướng đi phải theo",
        description = "Khi đặt biển số R.301a ở trước nơi đường giao nhau thì hiệu lực tác dụng của biển là có phạm vi khu vực nơi đường giao nhau phía sau biển tức là cấm xe rẽ phải hay rẽ trái. Nếu biển này đặt ở giữa đường (bắt đầu đường phố) thì hiệu lực tác dụng của biển sẽ từ vị trí đặt biển trở đi và không áp dụng cho các phương tiện đi vào đường giao nhau. Trong trường hợp đặt biển để rẽ trái quay lại thì sẽ không cấm xe rẽ phải trên đoạn đường đi từ biển đến đường giao nhau.",
        type = "hieulenh"
    ),
    TrafficSign(
        imageRes = R.drawable.huongphaidiphai,
        title = "Biển số R.301b \"Hướng đi phải theo",
        description = "Nhằm chỉ hướng cho phép xe đi ngang qua nơi đường giao nhau và ngăn cản hướng đi ngược chiều trên đường phố vội đường một chiều. Biển bắt buộc người tham gia giao thông chỉ được phép rẽ phải hoặc rẽ trái ở phạm vi nơi đường giao nhau trước mặt biển.",
        type = "hieulenh"
    ),
    TrafficSign(
        imageRes = R.drawable.huongphaiditrai,
        title = "Biển số R.301c \"Hướng đi phải theo",
        description = "Nhằm chỉ hướng cho phép xe đi ngang qua nơi đường giao nhau và ngăn chặn hướng đi ngược chiều trên đường phố với đường một chiều. Biển bắt buộc người tham gia giao thông chỉ được phép rẽ phải hoặc rẽ trái ở phạm vi nơi đường giao nhau trước mặt biển.",
        type = "hieulenh"
    ),
    TrafficSign(
        imageRes = R.drawable.huongphaidiphai,
        title = "Biển số R.302a \"Hướng phải đi vòng chướng ngại vật\"",
        description = "Để bảo các loại xe (cơ giới và thô sơ) hướng đi để qua một chướng ngại vật. Chỉ được vòng sang phải.",
        type = "hieulenh"
    ),
    TrafficSign(
        imageRes = R.drawable.huongphaiditrai,
        title = "Biển số R.302b \"Hướng phải đi vòng chướng ngại vật\"",
        description = "Để bảo các loại xe (cơ giới và thô sơ) hướng đi để qua một chướng ngại vật. Chỉ được vòng sang trái.",
        type = "hieulenh"
    ),
    TrafficSign(
        imageRes = R.drawable.tuyenduongcauvuotcatqua,
        title = "Biển số R.308a \"Tuyến đường cầu vượt cắt qua\"",
        description = "Biểu thị phía trước có cầu vượt, xe có thể đi thẳng hoặc theo chỉ dẫn trên hình vẽ để rẽ trái.",
        type = "hieulenh"
    ),
    TrafficSign(
        imageRes = R.drawable.camquaydau,
        title = "Biển chỉ dẫn 1",
        description = "Bệnh viện phía trước",
        type = "chidan"
    )
)

