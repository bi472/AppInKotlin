package com.example.myfirstappinkotlin.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.UserName
import com.example.domain.usecase.GetMqttSettingsUseCase
import com.example.domain.usecase.GetUserNameUseCase
import com.example.domain.usecase.SaveMqttSettingsUseCase
import com.example.domain.usecase.SaveUserNameUseCase

class MainViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase): ViewModel() {

    private var userNameLiveMutable = MutableLiveData<String>()
    val userNameLive: LiveData<String> = userNameLiveMutable

    init {
        Log.e("AAA", "VM created")
    }
}