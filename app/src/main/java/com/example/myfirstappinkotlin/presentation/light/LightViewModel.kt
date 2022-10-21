package com.example.myfirstappinkotlin.presentation.light

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.MQTTSettings
import com.example.domain.models.PublishMessage
import com.example.domain.models.SubscribeTopic
import com.example.domain.models.UserName
import com.example.domain.usecase.GetMqttSettingsUseCase
import com.example.domain.usecase.GetUserNameUseCase
import com.example.domain.usecase.MqttPublishUseCase
import com.example.domain.usecase.MqttSubscribeUseCase

class LightViewModel(
    private val mqttPublishUseCase: MqttPublishUseCase,
    private val mqttSubscribeUseCase: MqttSubscribeUseCase,
    private val getMqttSettingsUseCase: GetMqttSettingsUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {



    private var lightLiveMutable = MutableLiveData<String>()
    val lightLive: LiveData<String> = lightLiveMutable

    fun subscribe(topic: String){
        val mqttSettings: MQTTSettings = getMqttSettingsUseCase.execute()
        val userName: UserName = getUserNameUseCase.execute()
        val subscription: SubscribeTopic = SubscribeTopic(topic)
        val resultData = mqttSubscribeUseCase.execute(mqttSettings, userName, subscription)
        lightLiveMutable.value = "${resultData.condition}"
    }

    fun publish(topic: String, message: String){
        val mqttSettings: MQTTSettings = getMqttSettingsUseCase.execute()
        val userName: UserName = getUserNameUseCase.execute()
        val publishMessage: PublishMessage = PublishMessage(topic, message)
        mqttPublishUseCase.execute(mqttSettings, userName, publishMessage)
    }
}