package com.example.domain.usecase

import com.example.domain.models.UserName
import com.example.domain.repository.UserRepository

class SaveUserNameUseCase (private val userRepository: UserRepository){
    fun execute(param: UserName) : Boolean {
        return userRepository.saveUserName(param)
    }
}