package com.example.data.storage.sharedprefs

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.data.storage.MqttSettingsStorage
import com.example.data.storage.models.MqttSettingsData
import com.example.data.storage.models.UserNameData
import java.lang.Exception

private const val SHARED_PREFS_NAME = "username_prefs"
private const val KEY_SERVER_URI = "SERVER_URI"
private const val DEFAULT_SERVER_URI = "google.com"

class SharedPrefMQTTSettingsStorage(private val context: Context) : MqttSettingsStorage{

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(mqttSettings: MqttSettingsData): Boolean {
        return try {
            sharedPreferences.edit().putString(KEY_SERVER_URI, mqttSettings.serverUri).apply()
            true
        } catch (exception: Exception){
            Toast.makeText(context, "К сожалению, имя пользователя и пароль не сохранились(", Toast.LENGTH_LONG).show()
            Log.e("USERTAG", exception.toString())
            false
        }
    }

    override fun get(): MqttSettingsData {
        val serverUri = sharedPreferences.getString(KEY_SERVER_URI, DEFAULT_SERVER_URI) ?: DEFAULT_SERVER_URI
        return MqttSettingsData(serverUri = serverUri)
    }
}