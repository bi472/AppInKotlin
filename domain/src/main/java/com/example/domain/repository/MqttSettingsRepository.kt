package com.example.domain.repository

import com.example.domain.models.MQTTSettings

interface MqttSettingsRepository {
    fun saveMqttSettings(saveParam: MQTTSettings): Boolean
    fun getMqttSettings() : MQTTSettings
}