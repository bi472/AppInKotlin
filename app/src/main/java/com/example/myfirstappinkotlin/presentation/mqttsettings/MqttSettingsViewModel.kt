package com.example.myfirstappinkotlin.presentation.mqttsettings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.MQTTSettings
import com.example.domain.usecase.GetMqttSettingsUseCase
import com.example.domain.usecase.SaveMqttSettingsUseCase
import java.net.URL

class MqttSettingsViewModel(
    private val saveMqttSettingsUseCase: SaveMqttSettingsUseCase,
    private val getMqttSettingsUseCase: GetMqttSettingsUseCase
): ViewModel() {
    private var mqttSettingsLiveMutable = MutableLiveData<String>()
    val mqttSettingsLive: LiveData<String> = mqttSettingsLiveMutable

    fun save(serverURI: String){
        val params = MQTTSettings(serverURI)
        val resultData = saveMqttSettingsUseCase.execute(params)
        mqttSettingsLiveMutable.value = "Save result is $resultData"
    }
    fun load(){
        val mqttSettings = getMqttSettingsUseCase.execute()
        mqttSettingsLiveMutable.value = "Your server is ${mqttSettings.serverUri}"
    }
}