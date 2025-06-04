package com.example.finalproject.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.finalproject.model.ImageSource
import com.example.finalproject.model.TrafficNews
import com.example.finalproject.viewmodel.TrafficNewsViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TinTucScreen(navController: NavController, viewModel: TrafficNewsViewModel = viewModel()) {
    val newsArticles by viewModel.newsArticles.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        FeatureTopBar(title = "Tin Tức Giao Thông") {
            navController.popBackStack()
        }

        if (newsArticles.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Chưa có tin tức nào",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(newsArticles) { article ->
                    NewsArticleItem(article, navController)
                }
            }
        }
    }
}

@Composable
fun NewsArticleItem(article: TrafficNews, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
            navController.navigate("news_detail/${article.id}")
        }
        ,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = when (val img = article.imageRes) {
                    is ImageSource.Local -> painterResource(id = img.resId)
                    is ImageSource.Url -> rememberAsyncImagePainter(model = img.url)
                },
                contentDescription = article.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xFF01579B)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = article.summary,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xFF0288D1)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = article.publishedDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    navController: NavController,
    articleId: Int,
    viewModel: TrafficNewsViewModel = viewModel()
) {
    val newsArticles by viewModel.newsArticles.collectAsState()
    val article = newsArticles.find { it.id == articleId }

    if (article == null) {
        Text("Không tìm thấy bài viết")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        FeatureTopBar(title = "Chi Tiết Tin Tức") {
            navController.popBackStack()
        }

        LazyColumn {
            item {
                // Hiển thị chi tiết bài viết như bạn đã làm trước
                Image(
                    painter = when (val img = article.imageRes) {
                        is ImageSource.Local -> painterResource(id = img.resId)
                        is ImageSource.Url -> rememberAsyncImagePainter(model = img.url)
                    },
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF5F5F5))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF01579B)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.publishedDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF0288D1)
                )
            }
        }
    }
}
