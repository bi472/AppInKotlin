package com.example.domain.usecase

import com.example.domain.models.LightName
import com.example.domain.repository.LightNamesRepository

class GetAllLightNamesUseCase (private val lightNamesRepository: LightNamesRepository){
    fun execute(): List<LightName> {
        return lightNamesRepository.getAllLightNames()
    }
}