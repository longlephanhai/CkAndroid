package com.example.ckandroid.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ckandroid.data.model.UserModel
import com.example.ckandroid.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class UserViewModel : ViewModel() {
    private val _user = MutableStateFlow(UserModel())
    val user: StateFlow<UserModel> = _user.asStateFlow()

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()
    init {

    }

    fun handleRegisterUser(user: UserModel) {
        viewModelScope.launch {
            try {
                val response = ApiClient.api.registerUser(user)
                if (response.isSuccessful && response.body() != null) {
                    _user.value = response.body()!!
                    _toastMessage.value = "Đăng ký thành công"
                } else{
                    _toastMessage.value = "Đăng ký thất bại"
                }
                Log.d("PostViewModel Success", "Response: $response")
            } catch (e: Exception) {
                Log.e("PostViewModel Error", "Error", e)
                _toastMessage.value = "Lỗi kết nối!"
            }
        }
    }
}