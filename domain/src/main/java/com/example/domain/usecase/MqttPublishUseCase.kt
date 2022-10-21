package com.example.domain.usecase

import com.example.domain.models.MQTTSettings
import com.example.domain.models.PublishMessage
import com.example.domain.models.UserName
import com.example.domain.repository.MqttCommunicateRepository

class MqttPublishUseCase (private val mqttCommunicateRepository: MqttCommunicateRepository){
    fun execute(mqttSettings: MQTTSettings, userName: UserName, publishMessage: PublishMessage){
        return mqttCommunicateRepository.publish(mqttSettings = mqttSettings, userName = userName, publishMessage = publishMessage)
    }
}