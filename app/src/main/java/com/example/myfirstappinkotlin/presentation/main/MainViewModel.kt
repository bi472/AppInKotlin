package com.example.myfirstappinkotlin.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetMqttSettingsUseCase
import com.example.domain.usecase.GetUserNameUseCase
import com.example.domain.usecase.MqttPublishUseCase
import com.example.domain.usecase.MqttSubscribeUseCase

class MainViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getMqttSettingsUseCase: GetMqttSettingsUseCase,
    private val subscribeUseCase: MqttSubscribeUseCase,
    private val publishUseCase: MqttPublishUseCase): ViewModel() {
}