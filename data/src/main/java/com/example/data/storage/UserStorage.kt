package com.example.data.storage

import com.example.data.storage.models.UserNameData

interface UserStorage {
    fun save(user: UserNameData): Boolean
    fun get(): UserNameData
}