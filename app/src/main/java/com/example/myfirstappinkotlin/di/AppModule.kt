package com.example.myfirstappinkotlin.di

import com.example.myfirstappinkotlin.presentation.light.LightViewModel
import com.example.myfirstappinkotlin.presentation.main.MainViewModel
import com.example.myfirstappinkotlin.presentation.mqttsettings.MqttSettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel>{
        MainViewModel(
            getUserNameUseCase = get(),
            saveUserNameUseCase = get(),
        )
    }

    viewModel<MqttSettingsViewModel>(){
        MqttSettingsViewModel(
            getMqttSettingsUseCase = get(),
            saveMqttSettingsUseCase = get()
        )
    }

    viewModel<LightViewModel>(){
        LightViewModel(
            mqttPublishUseCase = get(),
            mqttSubscribeUseCase = get(),
            getMqttSettingsUseCase = get(),
            getUserNameUseCase = get()
        )
    }
}