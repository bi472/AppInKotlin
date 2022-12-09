package com.example.myfirstappinkotlin.di

import androidx.room.Room
import com.example.data.repository.LightNamesRepositoryImpl
import com.example.data.repository.MqttCommunicateRepositoryImpl
import com.example.data.repository.MqttSettingsRepostoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.LightNamesStorage
import com.example.data.storage.MqttCommunicateStorage
import com.example.data.storage.MqttSettingsStorage
import com.example.data.storage.UserStorage
import com.example.data.storage.database.LightDatabase
import com.example.data.storage.database.LightDatabaseDao
import com.example.data.storage.database.LightItem
import com.example.data.storage.database.LightRepository
import com.example.data.storage.mqttclients.MqttCommunicate
import com.example.data.storage.sharedprefs.SharedPrefMQTTSettingsStorage
import com.example.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.domain.repository.LightNamesRepository
import com.example.domain.repository.MqttCommunicateRepository
import com.example.domain.repository.MqttSettingsRepository
import com.example.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<UserStorage>{
        SharedPrefUserStorage(context = get())
    }

    single<UserRepository>{
        UserRepositoryImpl(userStorage = get())
    }

    single<MqttSettingsStorage>{
        SharedPrefMQTTSettingsStorage(context = get())
    }

    single<MqttSettingsRepository>{
        MqttSettingsRepostoryImpl(mqttSettingsStorage = get())
    }

    single <MqttCommunicateStorage>{
        MqttCommunicate(context = get())
    }

    single <MqttCommunicateRepository>{
        MqttCommunicateRepositoryImpl(mqttCommunicateStorage = get())
    }
    single {
        Room.databaseBuilder(
            get(),
            LightDatabase::class.java,
            "Lights"
        ).build()
    }
    single <LightNamesStorage>{
        LightRepository(lightDatabaseDao = get())
    }
    single <LightNamesRepository>{
        LightNamesRepositoryImpl(lightNamesStorage = get())
    }
    single {
        val database = get<LightDatabase>()
        database.lightDao()
    }
}