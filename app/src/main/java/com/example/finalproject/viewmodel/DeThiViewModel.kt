package com.example.finalproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Question
import com.example.finalproject.repository.DeThiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DeThiViewModel : ViewModel() {

    private val repository = DeThiRepository()

    private val _deThi = MutableStateFlow<List<Question>>(emptyList())
    val deThi: StateFlow<List<Question>> = _deThi

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadDeThi(categoryId: String, soCauCanLay: Int) {
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val cauThuong = repository.getCauHoiNgauNhien("cauhoi", 10)
                val cauDiemLiet = repository.getCauHoiNgauNhien("cauDiemLiet", 15)

                if (cauThuong.isEmpty() && cauDiemLiet.isEmpty()) {
                    _error.value = "Không tìm thấy câu hỏi"
                    _deThi.value = emptyList()
                } else {
                    val allQuestions = cauThuong + cauDiemLiet
                    _deThi.value = allQuestions
                }
            } catch (e: Exception) {
                _error.value = "Lỗi tải câu hỏi: ${e.message}"
                _deThi.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}
