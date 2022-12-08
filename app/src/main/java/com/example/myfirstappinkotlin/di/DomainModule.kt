package com.example.myfirstappinkotlin.di

import com.example.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory<GetUserNameUseCase> {
        GetUserNameUseCase(userRepository = get())
    }

    factory<SaveUserNameUseCase> {
        SaveUserNameUseCase(userRepository = get())
    }

    factory<GetMqttSettingsUseCase>{
        GetMqttSettingsUseCase(mqttSettingsRepository = get())
    }

    factory<SaveMqttSettingsUseCase>{
        SaveMqttSettingsUseCase(mqttSettingsRepository = get())
    }

    factory <MqttPublishUseCase>{
        MqttPublishUseCase(mqttCommunicateRepository = get())
    }

    factory <MqttSubscribeUseCase>{
        MqttSubscribeUseCase(mqttCommunicateRepository = get())
    }

    factory <GetAllLightNamesUseCase>{
        GetAllLightNamesUseCase(lightNamesRepository = get())
    }

    factory <GetTopicByNameUseCase>{
        GetTopicByNameUseCase(lightNamesRepository = get())
    }

    factory <InsertLightUseCase>{
        InsertLightUseCase(lightNamesRepository = get())
    }
}