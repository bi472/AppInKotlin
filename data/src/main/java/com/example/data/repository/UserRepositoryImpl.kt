package com.example.data.repository

import com.example.data.storage.models.UserNameData
import com.example.data.storage.UserStorage
import com.example.domain.models.MQTTSettings
import com.example.domain.models.UserName
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveUserName(saveParam: UserName): Boolean {

        val user = mapToStorage(saveParam)

        return userStorage.save(user)
    }

    override fun getUserName(): UserName {
        val user = userStorage.get()
        return mapToDomain(user)
    }

    private fun mapToStorage(saveParam: UserName): UserNameData{
        return UserNameData(saveParam.user, saveParam.password)
    }

    private fun mapToDomain(user: UserNameData): UserName{
        return UserName(user.user, user.password)
    }
}