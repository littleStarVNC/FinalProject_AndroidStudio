package com.example.finalproject.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

@Composable
fun TrafficSignImage(sign: TrafficSign) {
    val painter = when (val img = sign.imageRes) {
        is ImageSource.Local -> painterResource(id = img.resId)
        is ImageSource.Url -> rememberAsyncImagePainter(model = img.url)
    }

    Image(
        painter = painter,
        contentDescription = sign.title,
    )
}
