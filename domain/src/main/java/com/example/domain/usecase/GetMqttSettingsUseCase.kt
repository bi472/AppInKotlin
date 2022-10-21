package com.example.domain.usecase

import com.example.domain.models.MQTTSettings
import com.example.domain.repository.MqttSettingsRepository

class GetMqttSettingsUseCase (private val mqttSettingsRepository: MqttSettingsRepository) {
    fun execute(): MQTTSettings{
        return mqttSettingsRepository.getMqttSettings()
    }
}