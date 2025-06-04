package com.example.finalproject.repository

import com.example.finalproject.model.Question
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DeThiRepository {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getCauHoiNgauNhien(
        categoryId: String,
        soCauCanLay: Int
    ): List<Question> {
        val snapshot = firestore.collection("thuvienCauHoi")
            .document(categoryId)
            .collection("cacCauHoi")
            .get()
            .await()

        val allQuestions = snapshot.documents.map { doc ->
            val dapanA = doc.getString("dapanA") ?: ""
            val dapanB = doc.getString("dapanB") ?: ""
            val dapanC = doc.getString("dapanC") ?: ""
            val dapanD = doc.getString("dapanD") ?: ""

            val options = listOf(dapanA, dapanB, dapanC, dapanD).filter { it.isNotBlank() }

            val dapanDung = doc.getString("dapanDung") ?: ""

            val correctAnswerIndex = options.indexOf(dapanDung).takeIf { it >= 0 } ?: 0

            Question(
                id = doc.id,
                noiDung = doc.getString("noiDung") ?: "",
                options = options,
                correctAnswer = correctAnswerIndex.toString(),
            )
        }

        return allQuestions.shuffled().take(soCauCanLay)
    }
}
