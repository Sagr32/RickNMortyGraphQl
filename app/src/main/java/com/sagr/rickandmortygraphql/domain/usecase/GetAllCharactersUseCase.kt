package com.sagr.rickandmortygraphql.domain.usecase

import com.sagr.rickandmortygraphql.domain.repository.MainRepository
import javax.inject.Inject


class GetAllCharactersUseCase @Inject
constructor(
    private val repo: MainRepository
) {
     operator fun invoke() = repo.getAllCharactersPagingSource()
}