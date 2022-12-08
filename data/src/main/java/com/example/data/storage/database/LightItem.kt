package com.example.data.storage.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Lights")
data class LightItem(
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,

    @ColumnInfo(name = "light_name")
    val lightName: String,

    @ColumnInfo(name = "topic")
    val topic: String
)