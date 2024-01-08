package com.sagr.rickandmortygraphql.domain.repository

import androidx.paging.PagingSource
import com.apollographql.apollo3.api.ApolloResponse
import com.sagr.rickandmortygraphql.GetAllCharactersQuery
import com.sagr.rickandmortygraphql.GetSingleCharacterQuery
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getSingleCharacter(characterId: String): Flow<ApolloResponse<GetSingleCharacterQuery.Data>>
    fun getAllCharactersPagingSource(): PagingSource<Int, GetAllCharactersQuery.Result>
}