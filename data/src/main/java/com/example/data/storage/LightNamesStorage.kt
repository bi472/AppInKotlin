package com.example.data.storage

import com.example.data.storage.models.LightNameData

interface LightNamesStorage {
    fun getLightNames(): List<LightNameData>
    fun getTopicByName(lightNameData: LightNameData): LightNameData
    fun insertLight(lightNameData: LightNameData)
}