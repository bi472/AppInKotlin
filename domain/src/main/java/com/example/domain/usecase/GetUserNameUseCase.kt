package com.example.domain.usecase

import com.example.domain.models.MQTTSettings
import com.example.domain.models.UserName
import com.example.domain.repository.UserRepository

class GetUserNameUseCase (private val userRepository: UserRepository){
    fun execute(): UserName {
        return userRepository.getUserName()
    }
}