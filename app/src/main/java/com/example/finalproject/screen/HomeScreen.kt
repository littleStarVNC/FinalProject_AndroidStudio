package com.example.finalproject.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.finalproject.R
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )

    {
        TopBar()
        ImageCarousel()
        FeatureGrid(navController)
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "GPLX Hạng A1",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(Icons.Default.Settings, contentDescription = "Cài đặt")
    }
}

@OptIn(ExperimentalPagerApi::class)
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
            count = imageList.size,
            state = pagerState,
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
fun FeatureGrid(navController: NavController) {
    val features = listOf(
        Triple("Học lý thuyết", Icons.Default.MenuBook, "lythuyet"),
        Triple("Thi sát hạch", Icons.Default.Schedule, "thisathach"),
        Triple("Biển báo", Icons.Default.Report, "bienbao"),
        Triple("Mẹo thi", Icons.Default.TipsAndUpdates, "meothi"),
        Triple("Các câu sai", Icons.Default.Description, "caccausai"),
    )

    Column(modifier = Modifier.padding(16.dp)) {
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
fun FeatureCard(item: Triple<String, ImageVector, String>, navController: NavController) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .clickable { navController.navigate(item.third) },
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
            Icon(imageVector = item.second, contentDescription = null, modifier = Modifier.size(36.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.first, textAlign = TextAlign.Center)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureTopBar(title: String, onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}
