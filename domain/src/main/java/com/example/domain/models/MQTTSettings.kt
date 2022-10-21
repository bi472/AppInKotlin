package com.example.domain.models

private const val DEFAULT_CLIENT_ID = "default_client_id"

class MQTTSettings (val serverUri: String, val client_id: String = DEFAULT_CLIENT_ID)