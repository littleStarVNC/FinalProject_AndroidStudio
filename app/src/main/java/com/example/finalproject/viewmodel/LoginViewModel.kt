package com.example.finalproject.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var loginSuccess by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    fun login() {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Vui lòng nhập đầy đủ thông tin"
            return
        }

        isLoading = true
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        db.collection("users").document(userId).get()
                            .addOnSuccessListener { document ->
                                val name = document.getString("name") ?: "Người dùng"
                                errorMessage = "Xin chào $name"
                                loginSuccess = true
                                isLoading = false
                            }
                            .addOnFailureListener {
                                errorMessage = "Không thể lấy dữ liệu người dùng"
                                isLoading = false
                            }
                    } else {
                        errorMessage = "Lỗi xác thực người dùng"
                        isLoading = false
                    }
                } else {
                    errorMessage = "Đăng nhập thất bại: ${task.exception?.message}"
                    isLoading = false
                }
            }
    }
}
