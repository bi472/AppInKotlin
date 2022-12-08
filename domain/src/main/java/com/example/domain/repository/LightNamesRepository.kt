package com.example.domain.repository

import com.example.domain.models.LightName

interface LightNamesRepository {
    fun getAllLightNames(): List<LightName>
    fun getTopicByName(lightName: LightName): LightName
    fun insertLight(lightName: LightName)
}