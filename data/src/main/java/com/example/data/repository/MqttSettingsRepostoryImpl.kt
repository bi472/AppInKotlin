package com.example.data.repository

import android.util.Log
import com.example.data.storage.MqttSettingsStorage
import com.example.data.storage.models.MqttSettingsData
import com.example.domain.models.MQTTSettings
import com.example.domain.repository.MqttSettingsRepository

class MqttSettingsRepostoryImpl(private val mqttSettingsStorage: MqttSettingsStorage): MqttSettingsRepository {
    override fun saveMqttSettings(saveParam: MQTTSettings): Boolean {
        val serverURI = mapToStorage(saveParam)
        return mqttSettingsStorage.save(serverURI);
    }

    private fun mapToStorage(saveParam: MQTTSettings): MqttSettingsData {
        return MqttSettingsData(saveParam.serverUri)
    }

    override fun getMqttSettings(): MQTTSettings {
        val serverURI = mqttSettingsStorage.get()
        return mapToDomain(serverURI)
    }

    private fun mapToDomain(serverURI: MqttSettingsData): MQTTSettings {
        return MQTTSettings(serverURI.serverUri)
    }
}