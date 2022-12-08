package com.example.myfirstappinkotlin.presentation.mqttsettings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.LightName
import com.example.domain.models.MQTTSettings
import com.example.domain.usecase.GetAllLightNamesUseCase
import com.example.domain.usecase.GetMqttSettingsUseCase
import com.example.domain.usecase.InsertLightUseCase
import com.example.domain.usecase.SaveMqttSettingsUseCase
import java.net.URL

class MqttSettingsViewModel(
    private val saveMqttSettingsUseCase: SaveMqttSettingsUseCase,
    private val getMqttSettingsUseCase: GetMqttSettingsUseCase,
    private val insertLightUseCase: InsertLightUseCase,
    private val getAllLightNamesUseCase: GetAllLightNamesUseCase
): ViewModel() {
    private var lightNamesLiveMutable = MutableLiveData<List<LightName>>()
    val lightNamesLive: LiveData<List<LightName>> = lightNamesLiveMutable

    fun insert(lightName: LightName){
        insertLightUseCase.execute(lightName)
    }

    fun load() {
        lightNamesLiveMutable.value = getAllLightNamesUseCase.execute()
    }
}