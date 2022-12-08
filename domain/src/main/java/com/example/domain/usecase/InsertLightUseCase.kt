package com.example.domain.usecase

import com.example.domain.models.LightName
import com.example.domain.repository.LightNamesRepository

class InsertLightUseCase(private val lightNamesRepository: LightNamesRepository) {
    fun execute(lightName: LightName){
        return lightNamesRepository.insertLight(lightName)
    }
}