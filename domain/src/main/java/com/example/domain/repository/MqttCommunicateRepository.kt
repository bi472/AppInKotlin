package com.example.domain.repository

import com.example.domain.models.*

interface MqttCommunicateRepository {
    fun publish(mqttSettings: MQTTSettings, userName: UserName, publishMessage: PublishMessage)
    fun subscribe(mqttSettings: MQTTSettings, userName: UserName, subscribeTopic: SubscribeTopic): RelayStatus
}