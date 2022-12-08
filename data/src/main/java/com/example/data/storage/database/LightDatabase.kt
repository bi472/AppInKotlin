package com.example.data.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LightItem::class], version = 1, exportSchema = false)
abstract class LightDatabase: RoomDatabase(){
    abstract fun lightDao(): LightDatabaseDao

    companion object {

        private var INSTANCE: LightDatabase? = null

        fun getInstance(context: Context): LightDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LightDatabase::class.java,
                        "lights_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}