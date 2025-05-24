package com.example.finalproject.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.finalproject.model.Question

@Composable
fun ThiSatHachScreen(
    deThi: List<Question>,
    onSubmit: (Int, Boolean) -> Unit
) {
    var currentIndex by remember { mutableStateOf(0) }
    val answers = remember { mutableStateMapOf<Int, Int>() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Câu ${currentIndex + 1}/${deThi.size}", fontWeight = FontWeight.Bold)
        Text(deThi[currentIndex].question, fontSize = 20.sp, modifier = Modifier.padding(top = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        deThi[currentIndex].options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { answers[currentIndex] = index }
                    .background(
                        if (answers[currentIndex] == index) Color.Gray else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(option)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            if (currentIndex > 0) {
                Button(onClick = { currentIndex-- }) { Text("Quay lại") }
            }
            if (currentIndex < deThi.lastIndex) {
                Button(onClick = { currentIndex++ }) { Text("Tiếp theo") }
            } else {
                Button(onClick = {
                    val correct = answers.count { (i, ans) -> ans == deThi[i].correctIndex }
                    val hasDiemLiet = deThi.withIndex().any {
                        val selected = answers[it.index]
                        it.value.isDiemLiet && selected != null && selected != it.value.correctIndex
                    }
                    val passed = correct >= 32 && !hasDiemLiet
                    onSubmit(correct, passed)
                }) {
                    Text("Nộp bài")
                }
            }
        }
    }
}
