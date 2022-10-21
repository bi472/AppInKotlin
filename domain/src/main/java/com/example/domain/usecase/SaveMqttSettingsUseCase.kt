package com.example.domain.usecase

import com.example.domain.models.MQTTSettings
import com.example.domain.repository.MqttSettingsRepository

class SaveMqttSettingsUseCase (private val mqttSettingsRepository: MqttSettingsRepository){
    fun execute(param: MQTTSettings) : Boolean{
        return mqttSettingsRepository.saveMqttSettings(param)
    }
}