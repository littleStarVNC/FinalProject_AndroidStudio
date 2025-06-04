package com.example.finalproject.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.icons.filled.BrightnessMedium
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController,
                  isDarkTheme: Boolean,
                  onToggleDarkTheme: (Boolean) -> Unit,
                  onBackClick: () -> Unit)
{
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { androidx.compose.material3.Text("Cài đặt") },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = onBackClick) {
                        androidx.compose.material3.Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            )
            {
                SettingGroup {
                    SettingItem(
                        icon = Icons.Default.BrightnessMedium,
                        title = "Chế độ tối",
                        showSwitch = true,
                        switchChecked = isDarkTheme,
                        onSwitchToggle = { onToggleDarkTheme(it) }
                    )
                    SettingItem(
                        icon = Icons.Default.TextFields,
                        title = "Cỡ chữ",
                        onClick = { /* TODO: Mở chọn cỡ chữ */ }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                SettingGroup {
                    SettingItem(
                        icon = Icons.Default.DirectionsCar,
                        title = "Chọn hạng bằng thi",
                        value = "Hạng A1",
                        onClick = { /* TODO: Chọn hạng bằng */ }
                    )
                    SettingItem(
                        icon = Icons.Default.Delete,
                        title = "Xóa dữ liệu đã học",
                        onClick = { /* TODO: Xác nhận xóa */ }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                val context = LocalContext.current

                SettingGroup {
                    SettingItem(
                        icon = Icons.Default.Security,
                        title = "Chính sách bảo mật",
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/200gplx-privacy-policy"))
                            context.startActivity(intent)
                        }
                    )
                    SettingItem(
                        icon = Icons.Default.Help,
                        title = "Hỗ trợ",
                        onClick = {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:hotro@emyapp.com")
                                putExtra(Intent.EXTRA_SUBJECT, "Hỗ trợ ứng dụng GPLX")
                                putExtra(Intent.EXTRA_TEXT, "Xin chào, tôi cần hỗ trợ về...")
                            }

                            // Kiểm tra có app email hay không
                            if (intent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(intent)
                            }
                        }
                    )
                    SettingItem(
                        icon = Icons.Default.Share,
                        title = "Chia sẻ ứng dụng với bạn bè",
                        onClick = {
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, "Ứng dụng thi GPLX")
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Hãy thử ngay ứng dụng thi GPLX này nhé: https://play.google.com/store/apps/details?id=com.example.finalproject"
                                )
                            }
                            context.startActivity(Intent.createChooser(intent, "Chia sẻ qua"))
                        }
                    )
                    SettingItem(
                        icon = Icons.Default.StarRate,
                        title = "Đánh giá ứng dụng",
                        onClick = {
                            val uri = Uri.parse("market://details?id=com.example.finalproject")
                            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }

                            // Nếu CH Play không khả dụng, fallback sang trình duyệt
                            if (intent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(intent)
                            } else {
                                val webIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.example.finalproject")
                                )
                                context.startActivity(webIntent)
                            }
                        }
                    )
                }
            }
        }
    )
}
@Composable
fun SettingGroup(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F8F8), shape = RoundedCornerShape(16.dp))
            .padding(8.dp)
    ) {
        content()
    }
}

@Composable
fun SettingItem(
    icon: ImageVector,
    title: String,
    value: String? = null,
    showSwitch: Boolean = false,
    switchChecked: Boolean = false,
    onSwitchToggle: ((Boolean) -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick?.invoke() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            modifier = Modifier.weight(1f)
        )
        if (showSwitch && onSwitchToggle != null) {
            Switch(
                checked = switchChecked,
                onCheckedChange = onSwitchToggle
            )
        } else if (value != null) {
            Text(text = value)
        }
    }
}
