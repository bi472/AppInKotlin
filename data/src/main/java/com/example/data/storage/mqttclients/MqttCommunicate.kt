package com.example.data.storage.mqttclients

import android.content.Context
import android.util.Log
import com.example.data.storage.MqttCommunicateStorage
import com.example.data.storage.models.*
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import java.lang.Exception

private const val TAG_KEY: String = "MQTTCLIENT"
private const val SUBSCRIBE_TAG: String = "_Subscribe"

class MqttCommunicate(private val context: Context) : MqttCommunicateStorage {

    private lateinit var mqttClient: MqttAndroidClient
    private lateinit var mqttConnectOptions: MqttConnectOptions
    private lateinit var disconnectedBufferOptions: DisconnectedBufferOptions

    private fun setMQTTClient(mqttSettingsData: MqttSettingsData) {
        val serverURI = mqttSettingsData.serverUri
        val client_id = mqttSettingsData.client_id
        mqttClient = MqttAndroidClient(context, serverURI, client_id)
    }

    private fun setMQTTConnectOptions(userNameData: UserNameData) {
        mqttConnectOptions = MqttConnectOptions()
        mqttConnectOptions.isAutomaticReconnect = true
        mqttConnectOptions.isCleanSession = true
        mqttConnectOptions.userName = userNameData.user
        mqttConnectOptions.password = userNameData.password.toCharArray()
    }

    private fun setDisconnectedOptions() {
        disconnectedBufferOptions = DisconnectedBufferOptions()
        disconnectedBufferOptions.isBufferEnabled = true
        disconnectedBufferOptions.bufferSize = 100
        disconnectedBufferOptions.isPersistBuffer = false
        disconnectedBufferOptions.isDeleteOldestMessages = false
    }

    override fun publish(
        mqttSettingsData: MqttSettingsData,
        userNameData: UserNameData,
        publishMessageData: PublishMessageData
    ) {
        setMQTTClient(mqttSettingsData)
        setMQTTConnectOptions(userNameData)
        try {
            val token = mqttClient.connect(mqttConnectOptions)
            token.actionCallback = object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    Log.i("Connection", "success ")
                    val topic = publishMessageData.topic
                    val pubMessage = publishMessageData.message
                    val qos = 1
                    val retained = false

                    val mqttMessage = MqttMessage()
                    mqttMessage.payload = pubMessage.toByteArray()
                    mqttMessage.qos = qos
                    mqttMessage.isRetained = retained

                    mqttClient.publish(topic, mqttMessage, null, object : IMqttActionListener {
                        override fun onSuccess(asyncActionToken: IMqttToken?) {
                            Log.i(TAG_KEY, "$pubMessage published to $topic")
                        }

                        override fun onFailure(
                            asyncActionToken: IMqttToken?,
                            exception: Throwable?
                        ) {
                            Log.e(TAG_KEY, "Failed to publish $pubMessage to $topic")
                        }
                    })
                    mqttClient.disconnect()
                }

                override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                    //connectionStatus = false
                    Log.e(TAG_KEY, "Connection failure")
                    // Give your callback on connection failure here
                    exception.printStackTrace()
                }
            }
        } catch (e: MqttException) {
            // Give your callback on connection failure here
            e.printStackTrace()
        }
    }


    override fun subscribe(
        mqttSettingsData: MqttSettingsData,
        userNameData: UserNameData,
        subscribeTopicData: SubscribeTopicData
    ): RelayStatusData {
        mqttSettingsData.client_id = mqttSettingsData.client_id + SUBSCRIBE_TAG
        setMQTTClient(mqttSettingsData)
        setMQTTConnectOptions(userNameData)
        setDisconnectedOptions()
        var relayMessage = RelayStatusData("Default")
        try {
            val token = mqttClient.connect(mqttConnectOptions)
            token.actionCallback = object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    mqttClient.setBufferOpts(disconnectedBufferOptions)
                    val topic = subscribeTopicData.topic
                    val qos = 1
                    mqttClient.subscribe(topic, qos, null, object : IMqttActionListener {
                        override fun onSuccess(asyncActionToken: IMqttToken?) {
                            Log.i(TAG_KEY, "Subscribed to $topic")
                        }

                        override fun onFailure(
                            asyncActionToken: IMqttToken?,
                            exception: Throwable?
                        ) {
                            Log.e(TAG_KEY, "Failed to subscribe $topic\nexception.toString()")
                        }

                    })
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    exception?.printStackTrace()
                }
            }
            mqttClient.setCallback(object : MqttCallback{
                override fun connectionLost(cause: Throwable?) {
                    Log.e(TAG_KEY, cause?.message.toString())
                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    Log.i(TAG_KEY, "Receive message: ${message.toString()} from topic: $topic")
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {
                    TODO("Not yet implemented")
                }

            })
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return relayMessage
    }
}