package com.example.data.storage.models

private const val DEFAULT_CLIENT_ID = "default_client_id"

class MqttSettingsData (var serverUri: String, var client_id: String = DEFAULT_CLIENT_ID)