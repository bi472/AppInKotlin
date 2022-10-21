package com.example.domain.usecase

import com.example.domain.models.MQTTSettings
import com.example.domain.models.RelayStatus
import com.example.domain.models.SubscribeTopic
import com.example.domain.models.UserName
import com.example.domain.repository.MqttCommunicateRepository

class MqttSubscribeUseCase (private val mqttCommunicateRepository: MqttCommunicateRepository){
    fun execute(mqttSettings: MQTTSettings, userName: UserName, subscribeTopic: SubscribeTopic): RelayStatus{
        return mqttCommunicateRepository.subscribe(mqttSettings, userName, subscribeTopic)
    }
}