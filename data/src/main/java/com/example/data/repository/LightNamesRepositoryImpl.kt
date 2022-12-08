package com.example.data.repository

import com.example.data.storage.LightNamesStorage
import com.example.data.storage.models.LightNameData
import com.example.domain.models.LightName
import com.example.domain.repository.LightNamesRepository

class LightNamesRepositoryImpl (private val lightNamesStorage: LightNamesStorage): LightNamesRepository{
    override fun getAllLightNames(): List<LightName> {
        val lightNamesDataList = lightNamesStorage.getLightNames()
        return mapToDomainAllLightNames(lightNamesDataList);
    }

    override fun getTopicByName(lightName: LightName): LightName {
        val data = lightNamesStorage.getTopicByName(mapToStorageLightName(lightName))
        return mapToDomainLightName(data)
    }

    private fun mapToDomainLightName(lightNameData: LightNameData): LightName {
        return LightName(lightNameData.roomName, lightNameData.topic)
    }

    override fun insertLight(lightName: LightName){
        lightNamesStorage.insertLight(mapToStorageLightName(lightName))
    }

    private fun mapToDomainAllLightNames(lightNameDataList: List<LightNameData>) : List<LightName> {
        val lightNameList: List<LightName> = emptyList()
        for (item in lightNameDataList){
            var lightNameDataList= LightName(item.roomName, item.topic)
            lightNameList.toMutableList().add(lightNameDataList)
        }
        return lightNameList
    }

    private fun mapToStorageLightName(lightName: LightName): LightNameData{
        return LightNameData(lightName.roomName, lightName.topic)
    }
}