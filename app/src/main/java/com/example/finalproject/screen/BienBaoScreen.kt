package com.example.finalproject.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finalproject.model.TrafficSign
import com.example.finalproject.repository.TrafficSignRepository
import com.example.finalproject.enums.TrafficSignType
import com.example.finalproject.viewmodel.TrafficSignViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BienBaoScreen(
    navController: NavController,
    viewModel: TrafficSignViewModel = viewModel()
) {
    var currentTab by remember { mutableStateOf(0) }
    val tabs = listOf("Học Biển Báo", "Kiểm Tra Kiến Thức", "Yêu Thích")

    Column(modifier = Modifier.fillMaxSize()) {
        FeatureTopBar(title = "Biển Báo Giao Thông") {
            navController.popBackStack()
        }

        TabRow(selectedTabIndex = currentTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = currentTab == index,
                    onClick = { currentTab = index },
                    text = { Text(title) }
                )
            }
        }

        when (currentTab) {
            0 -> LearnTrafficSignsContent(viewModel)
            1 -> TrafficSignsQuizContent()
            2 -> FavoriteSignsContent(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnTrafficSignsContent(viewModel: TrafficSignViewModel) {
    val categories = listOf(
        TrafficSignType.ALL,
        TrafficSignType.PROHIBITION,
        TrafficSignType.MANDATORY,
        TrafficSignType.WARNING,
        TrafficSignType.INFORMATION
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Search Bar
        OutlinedTextField(
            value = viewModel.searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            label = { Text("Tìm kiếm biển báo") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true
        )

        // Category Filters
        Text(
            "Chọn loại biển báo:",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            modifier = Modifier.padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                FilterChip(
                    selected = viewModel.selectedCategory == category,
                    onClick = { viewModel.updateCategory(category) },
                    label = { Text(category.displayName) }
                )
            }
        }

        // Results Count
        Text(
            "Tìm thấy ${viewModel.filteredSigns.size} biển báo",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Traffic Signs List
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.filteredSigns) { sign ->
                EnhancedTrafficSignItem(
                    sign = sign,
                    isFavorite = viewModel.isFavorite(sign.id),
                    onFavoriteClick = { viewModel.toggleFavorite(sign.id) }
                )
            }
        }
    }
}

@Composable
fun EnhancedTrafficSignItem(
    sign: TrafficSign,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = sign.imageRes),
                    contentDescription = sign.title,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = sign.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row {
                        AssistChip(
                            onClick = { },
                            label = { Text(sign.type.displayName) },
                            modifier = Modifier.padding(end = 8.dp)
                        )

                        AssistChip(
                            onClick = { },
                            label = { Text(sign.difficulty.displayName) }
                        )
                    }
                }

                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite) "Bỏ yêu thích" else "Yêu thích",
                        tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider()
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Mô tả:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = sign.description ?: "Không có mô tả",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun FavoriteSignsContent(viewModel: TrafficSignViewModel) {
    val favoriteSignIds = viewModel.favoriteSignIds
    val allSigns = TrafficSignRepository().getAllTrafficSigns()
    val favoriteSigns = allSigns.filter { favoriteSignIds.contains(it.id) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Biển báo yêu thích",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (favoriteSigns.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Chưa có biển báo yêu thích nào",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(favoriteSigns) { sign ->
                    EnhancedTrafficSignItem(
                        sign = sign,
                        isFavorite = true,
                        onFavoriteClick = { viewModel.toggleFavorite(sign.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun TrafficSignsQuizContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Tính năng kiểm tra",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Sẽ được phát triển trong phiên bản tiếp theo",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}