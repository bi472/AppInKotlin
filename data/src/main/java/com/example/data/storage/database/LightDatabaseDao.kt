package com.example.data.storage.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.storage.models.LightNameData

@Dao
interface LightDatabaseDao {
    @Query("Select * from Lights")
    fun getAll(): LiveData<List<LightItem>>
    @Query("Select topic from Lights where light_name = :lightName")
    fun getTopicByName(lightName: String): LiveData<LightItem>
    @Insert
    fun insert(item: LightItem)
}