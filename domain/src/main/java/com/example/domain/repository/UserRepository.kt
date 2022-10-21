package com.example.domain.repository

import com.example.domain.models.UserName

interface UserRepository {
    fun saveUserName(saveParam: UserName): Boolean
    fun getUserName(): UserName
}