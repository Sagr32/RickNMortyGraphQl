package com.sagr.rickandmortygraphql.domain.usecase

import com.sagr.rickandmortygraphql.domain.repository.MainRepository
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject
constructor(
    private val repo: MainRepository
) {
    suspend operator fun invoke(id:String) = repo.getSingleCharacter(id)
}