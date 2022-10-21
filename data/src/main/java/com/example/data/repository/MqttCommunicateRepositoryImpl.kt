package com.example.data.repository

import com.example.data.storage.MqttCommunicateStorage
import com.example.data.storage.models.*
import com.example.domain.models.*
import com.example.domain.repository.MqttCommunicateRepository

class MqttCommunicateRepositoryImpl (private val mqttCommunicateStorage: MqttCommunicateStorage ): MqttCommunicateRepository{
    override fun publish(
        mqttSettings: MQTTSettings,
        userName: UserName,
        publishMessage: PublishMessage
    ) {
        mqttCommunicateStorage.publish(
            mapToStorageMQTT(mqttSettings),
            mapToStorageUser(userName),
            mapToStoragePublish(publishMessage)
        )
    }

    override fun subscribe(
        mqttSettings: MQTTSettings,
        userName: UserName,
        subscribeTopic: SubscribeTopic
    ): RelayStatus {
        val status = mqttCommunicateStorage.subscribe(
            mapToStorageMQTT(mqttSettings),
            mapToStorageUser(userName),
            mapToStorageSub(subscribeTopic)
        )
        return mapToDomainRelay(status)
    }

    private fun mapToStorageMQTT(mqttSettings: MQTTSettings): MqttSettingsData {
        return MqttSettingsData(mqttSettings.serverUri)
    }

    private fun mapToStorageUser(userName: UserName): UserNameData{
        return UserNameData(userName.user, userName.password)
    }

    private fun mapToStoragePublish(publishMessage: PublishMessage): PublishMessageData{
        return PublishMessageData(publishMessage.topic, publishMessage.message)
    }

    private fun mapToStorageSub(subscribeTopic: SubscribeTopic): SubscribeTopicData{
        return SubscribeTopicData(subscribeTopic.topic)
    }

    private fun mapToDomainRelay(statusRelayStatus: RelayStatusData): RelayStatus{
        return RelayStatus(statusRelayStatus.condition)
    }
}