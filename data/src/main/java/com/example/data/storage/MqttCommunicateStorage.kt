package com.example.data.storage

import com.example.data.storage.models.*

interface MqttCommunicateStorage {
    fun publish(mqttSettingsData: MqttSettingsData, userNameData: UserNameData, publishMessageData: PublishMessageData)
    fun subscribe(mqttSettingsData: MqttSettingsData, userNameData: UserNameData, subscribeTopicData: SubscribeTopicData): RelayStatusData
}