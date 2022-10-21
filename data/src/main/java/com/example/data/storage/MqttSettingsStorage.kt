package com.example.data.storage

import com.example.data.storage.models.MqttSettingsData

interface MqttSettingsStorage {
    fun save(mqttSettings: MqttSettingsData): Boolean
    fun get(): MqttSettingsData
}