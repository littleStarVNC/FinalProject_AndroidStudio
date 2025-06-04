package com.example.finalproject.repository


import com.example.finalproject.R
import com.example.finalproject.model.TrafficSign
import com.example.finalproject.enums.TrafficSignType
import com.example.finalproject.enums.Difficulty
import com.example.finalproject.model.ImageSource

class TrafficSignRepository {
    private val trafficSigns = listOf(
        TrafficSign(
            id = 1,
            imageRes = ImageSource.Local(R.drawable.camdinguocchieu),
            title = "Cấm đi ngược chiều",
            type = TrafficSignType.PROHIBITION,
            description = "Biển báo này cấm các phương tiện đi ngược chiều đường quy định",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 2,
            imageRes = ImageSource.Local(R.drawable.camxemay),
            title = "Cấm xe máy",
            type = TrafficSignType.PROHIBITION,
            description = "Biển báo cấm xe mô tô, xe gắn máy đi qua",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 3,
            imageRes = ImageSource.Local(R.drawable.huongphaiditheophai),
            title = "Hướng phải đi theo phải",
            type = TrafficSignType.MANDATORY,
            description = "Các phương tiện chỉ được phép rẽ phải",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 4,
            imageRes = ImageSource.Local(R.drawable.huongphaiditheotrai),
            title = "Hướng phải đi theo trái",
            type = TrafficSignType.MANDATORY,
            description = "Các phương tiện chỉ được phép rẽ trái",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 5,
            imageRes = ImageSource.Url("https://cdn.thuvienphapluat.vn/uploads/cong-dong-dan-luat/2024/thang-8/30/bien-bao/p-127.png"),
            title = "Cấm tốc độ tối đa",
            type = TrafficSignType.PROHIBITION,
            description = "Biển báo cấm các phương tiện đi quá tốc độ quy định (thường ghi số km/h trên biển)",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 6,
            imageRes = ImageSource.Url("https://carpla.vn/blog/wp-content/uploads/2023/12/y-nghia-bien-cam-dung-do.jpg"),
            title = "Cấm dừng và đỗ xe",
            type = TrafficSignType.PROHIBITION,
            description = "Biển báo cấm các phương tiện dừng hoặc đỗ xe tại khu vực có biển",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 7,
            imageRes = ImageSource.Url("https://bizweb.dktcdn.net/thumb/grande/100/352/036/products/bien-301-a.jpg?v=1600053811687"),
            title = "Chỉ được đi thẳng",
            type = TrafficSignType.MANDATORY,
            description = "Các phương tiện chỉ được phép đi thẳng, không được rẽ trái hoặc phải",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 8,
            imageRes = ImageSource.Url("https://lh4.googleusercontent.com/proxy/iMzH3Gu_ukcBDVs3u7Y8Pu_jNWtH8nnFNmx72rKT22tZ6pXO9por5DGWf494QAmppsmxICwrUnxEdOuwFfCb52ev2JS7Hq16ZVwTAOaQWWCMNIoa0gERXZjsayfWIMct_Eo"),
            title = "Cảnh báo đường hẹp",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo đoạn đường phía trước bị hẹp, yêu cầu giảm tốc độ và cẩn thận",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 9,
            imageRes = ImageSource.Url("https://bizweb.dktcdn.net/thumb/grande/100/352/036/products/114838183-45-1335984544-45-8-ec452-jpeg.jpg?v=1600225201843"),
            title = "Cảnh báo giao nhau",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo có giao lộ phía trước, yêu cầu giảm tốc độ và nhường đường",
            difficulty = Difficulty.HARD
        ),
        TrafficSign(
            id = 10,
            imageRes = ImageSource.Url("https://bizweb.dktcdn.net/100/415/690/files/cac-bien-bao-toc-do-2.jpg?v=1677145397513"),
            title = "Hết giới hạn tốc độ",
            type = TrafficSignType.MANDATORY,
            description = "Biển báo thông báo kết thúc khu vực giới hạn tốc độ trước đó",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 11,
            imageRes = ImageSource.Url("https://bizweb.dktcdn.net/100/352/036/files/bien-bao-103.jpg?v=1575705330590"),
            title = "Cấm ô tô",
            type = TrafficSignType.PROHIBITION,
            description = "Biển báo cấm các loại ô tô đi qua",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 12,
            imageRes = ImageSource.Url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcrlKe_b1g8Qa5F0qRB5Nr-CSv65LXqiwL-A&s"),
            title = "Cấm rẽ lưới",
            type = TrafficSignType.PROHIBITION,
            description = "Biển báo cấm các phương tiện rẽ lưới tại giao lộ",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 13,
            imageRes = ImageSource.Url("https://images.tth.eu/queviet/b55d601eabeb95135bf2a1ea2cf2ae05.jpg"),
            title = "Cấm quay xe",
            type = TrafficSignType.PROHIBITION,
            description = "Biển báo cấm các phương tiện quay đầu xe tại khu vực có biển",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 14,
            imageRes = ImageSource.Url("https://myanhsafety.com.vn/image/catalog/BIEN-BAO-GIAO-THONG-GIOI-HAN-TAI-TRONG-XE---10-TAN-2.jpg"),
            title = "Cảnh báo tải trọng",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo giới hạn tải trọng tối đa của cầu hoặc đường",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 15,
            imageRes = ImageSource.Url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMbue575ZzAfqcpnZShxKq-F-Yl6OQbTwxZw&s"),
            title = "Cảnh báo đường trơn",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo đoạn đường phía trước dễ trơn trượt, yêu cầu giảm tốc độ",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 16,
            imageRes = ImageSource.Url("https://www.thietbigiaothong24h.com/DesktopModules/Store/Thumbnail.aspx?IP=/Portals/27907/Bien%20canh%20bao%20nguy%20hiem/Bien-bao-219_jpg.jpg&IW=432"),
            title = "Cảnh báo đường dốc",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo đoạn đường phía trước có dốc lên hoặc xuống, yêu cầu cẩn thận",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 17,
            imageRes = ImageSource.Url("https://bizweb.dktcdn.net/thumb/grande/100/352/036/products/bien-301f.jpg?v=1600059927560"),
            title = "Chỉ được đi thẳng hoặc rẽ phải",
            type = TrafficSignType.MANDATORY,
            description = "Các phương tiện chỉ được phép đi thẳng hoặc rẽ phải",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 18,
            imageRes = ImageSource.Url("https://bizweb.dktcdn.net/thumb/grande/100/352/036/products/bien-301f.jpg?v=1600059927560"),
            title = "Chỉ được đi thẳng hoặc rẽ trái",
            type = TrafficSignType.MANDATORY,
            description = "Các phương tiện chỉ được phép đi thẳng hoặc rẽ trái",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 19,
            imageRes = ImageSource.Url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUivIrLyra9ibna948Mov8yOZYj7F0iSm5NGy3oFYPswT3wrmI3l8myAEfNyvv9GxYXTw&usqp=CAU"),
            title = "Nhường đường",
            type = TrafficSignType.MANDATORY,
            description = "Biển báo yêu cầu các phương tiện phải nhường đường cho các phương tiện khác",
            difficulty = Difficulty.HARD
        ),
        TrafficSign(
            id = 20,
            imageRes = ImageSource.Url("https://www.thietbithinhphat.com/wp-content/uploads/2023/01/Bien-canh-bao-nguy-hiem-Tre-em-qua-duong-510x510-1.jpg"),
            title = "Cảnh báo trẻ em",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo khu vực có trẻ em qua đường, yêu cầu giảm tốc độ",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 21,
            imageRes = ImageSource.Url("https://autopro8.mediacdn.vn/KNa3v6PxbsnRMeRggcccccccccccc5/Image/2012/04/35_2b6e5.jpg"),
            title = "Cảnh báo động vật",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo khu vực có động vật qua đường, yêu cầu giảm tốc độ",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 22,
            imageRes = ImageSource.Url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSf2w3bktTcHiSuY0SeiRPzqY8LrB3KUsKhnohBLtE_Huq3pZ4HwQ8Yc5pN_6o47sNU9wE&usqp=CAU"),
            title = "Đường ưu tiên",
            type = TrafficSignType.MANDATORY,
            description = "Biển báo chỉ đường ưu tiên cho các phương tiện tại giao lộ",
            difficulty = Difficulty.HARD
        ),
        TrafficSign(
            id = 23,
            imageRes = ImageSource.Url("https://lh6.googleusercontent.com/proxy/IZuMQ9YuLlqKNYbcU0j-DxbMaCvLuBY70mTL1atGmF-QSsyZAgHqozhZ9O9Bvsza1OwFpUmr7Pr5Scs4cJPlQS39gjr1NVAYx3NrKj0ls7XDl8sh2hA3Kuo_9fHAwalhZLyW4QCfwonImuMyVDYgdq985_CqI7M4gbJIqWjUcg"),
            title = "Hết đường ưu tiên",
            type = TrafficSignType.MANDATORY,
            description = "Biển báo thông báo kết thúc đoạn đường ưu tiên",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 24,
            imageRes = ImageSource.Url("https://bizweb.dktcdn.net/100/340/056/products/1036450bien-bao-cong-truong-jpeg.jpg?v=1543543488993"),
            title = "Cảnh báo công trường",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo khu vực đang thi công, yêu cầu giảm tốc độ và cẩn thận",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 25,
            imageRes = ImageSource.Url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXfEbjaGdegKqwC8R9Ai4GWaTisoOJHJ3YPg&s"),
            title = "Cảnh báo vật cản",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo có vật cản trên đường, yêu cầu giảm tốc độ",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 26,
            imageRes = ImageSource.Url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSW_7WWE48ZEtmMRcIliPJmQzRYETHy1hcf_g&s"),
            title = "Tốc độ tối đa 40",
            type = TrafficSignType.MANDATORY,
            description = "Biển báo quy định tốc độ tối đa cho phép trên đoạn đường",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 27,
            imageRes = ImageSource.Url("https://media.istockphoto.com/id/530225397/vi/anh/c%C3%B4ng-tr%C6%B0%E1%BB%9Dng-x%C3%A2y-d%E1%BB%B1ng.jpg?s=612x612&w=0&k=20&c=UQ4JnQM3qnBx-xMuhzA1iLTdDahdI_B05mE3KCTcIP8="),
            title = "Cảnh báo đường đang xây",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo đoạn đường đang được xây dựng hoặc sửa chữa",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 28,
            imageRes = ImageSource.Url("https://media.istockphoto.com/id/530225397/vi/anh/c%C3%B4ng-tr%C6%B0%E1%BB%9Dng-x%C3%A2y-d%E1%BB%B1ng.jpg?s=612x612&w=0&k=20&c=UQ4JnQM3qnBx-xMuhzA1iLTdDahdI_B05mE3KCTcIP8="),
            title = "Nơi đỗ xe",
            type = TrafficSignType.MANDATORY,
            description = "Biển báo chỉ khu vực được phép đỗ xe",
            difficulty = Difficulty.EASY
        ),
        TrafficSign(
            id = 29,
            imageRes = ImageSource.Url("https://lh6.googleusercontent.com/proxy/54pvr5Df0Zb4ses1ohilJf4YlKGE571XD7PDDqhldS3jvFRA1riWJEa1GpBW6QYbFX4FNJY5RVIiWnB3k9ZJss4btA"),
            title = "Cảnh báo cầu hẹp",
            type = TrafficSignType.WARNING,
            description = "Biển báo cảnh báo cầu phía trước có chiều ngang hẹp, yêu cầu cẩn thận",
            difficulty = Difficulty.MEDIUM
        ),
        TrafficSign(
            id = 30,
            imageRes = ImageSource.Url("https://bhldgialong.com/wp-content/uploads/2020/05/bien-bao-dung-lai.jpg"),
            title = "Dừng lại",
            type = TrafficSignType.MANDATORY,
            description = "Biển báo yêu cầu tất cả các phương tiện phải dừng lại tại giao lộ",
            difficulty = Difficulty.HARD
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