package com.example.finalproject.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.R
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(navController = navController, title = "Trang chủ", showBackButton = false)
        ImageCarousel()
        FeatureGrid(navController)
    }
}




@Composable
fun TopBar(navController: NavHostController, title: String, showBackButton: Boolean = true) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Hiển thị nút Back nếu showBackButton = true
        if (showBackButton) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }

        // Hiển thị tiêu đề
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        // Hiển thị icon Settings
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Cài đặt"
        )
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun ImageCarousel() {
    val pagerState = rememberPagerState()
    val imageList = listOf(R.drawable.img, R.drawable.img_1)

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % imageList.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            state = pagerState,
            count = imageList.size,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(16.dp)
        ) { page ->
            Image(
                painter = painterResource(id = imageList[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun FeatureGrid(navController: NavHostController) {
    val features = listOf(
        "Học lý thuyết" to Icons.Default.MenuBook,
        "Thi sát hạch" to Icons.Default.Schedule,
        "Biển báo" to Icons.Default.Report,
        "Mẹo thi" to Icons.Default.TipsAndUpdates,
        "Các câu sai" to Icons.Default.Description,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        for (i in features.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FeatureCard(features[i], navController)
                if (i + 1 < features.size) {
                    FeatureCard(features[i + 1], navController)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun FeatureCard(pair: Pair<String, ImageVector>, navController: NavHostController) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(4.dp)
            .clickable {
                // Điều hướng đến màn hình tương ứng
                when (pair.first) {
                    "Học lý thuyết" -> navController.navigate("ly_thuyet")
                    "Thi sát hạch" -> navController.navigate("thi_sat_hach")
                    "Biển báo" -> navController.navigate("bien_bao")
                    "Mẹo thi" -> navController.navigate("meo_thi")
                    "Các câu sai" -> navController.navigate("cac_cau_sai")
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = pair.second,
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = pair.first, textAlign = TextAlign.Center)
        }
    }
}


