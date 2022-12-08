package com.example.data.storage.database

import androidx.lifecycle.LiveData
import com.example.data.storage.LightNamesStorage
import com.example.data.storage.models.LightNameData
import com.example.domain.models.LightName

class LightRepository(private val lightDatabaseDao: LightDatabaseDao):LightNamesStorage{

    override fun getLightNames(): List<LightNameData> {
        val readAllData: LiveData<List<LightItem>> = lightDatabaseDao.getAll()
        var lightNamesList: List<LightNameData> = emptyList()
        for (item in readAllData.value!!){
            var lightNameData= LightNameData(item.lightName, item.topic)
            lightNamesList.toMutableList().add(lightNameData)
        }
        return lightNamesList
    }

    override fun getTopicByName(lightNameData: LightNameData): LightNameData {
        val readAllData: LiveData<LightItem> = lightDatabaseDao.getTopicByName(lightNameData.roomName)
        return LightNameData(readAllData.value!!.lightName, readAllData.value!!.topic)
    }

    override fun insertLight(lightNameData: LightNameData) {
        lightDatabaseDao.insert(LightItem(lightName = lightNameData.roomName, topic = lightNameData.topic))
    }
}