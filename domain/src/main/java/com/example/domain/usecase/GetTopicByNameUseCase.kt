package com.example.domain.usecase

import com.example.domain.models.LightName
import com.example.domain.repository.LightNamesRepository

class GetTopicByNameUseCase (private val lightNamesRepository: LightNamesRepository){
    fun execute(lightName: LightName): LightName{
        return lightNamesRepository.getTopicByName(lightName)
    }
}