package com.sagr.rickandmortygraphql.data.repository

import androidx.paging.PagingSource
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.sagr.rickandmortygraphql.GetAllCharactersQuery
import com.sagr.rickandmortygraphql.GetSingleCharacterQuery
import com.sagr.rickandmortygraphql.data.network.NetworkMonitor
import com.sagr.rickandmortygraphql.data.paging_sources.CharactersResultPagingSource
import com.sagr.rickandmortygraphql.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val networkMonitor: NetworkMonitor
) : MainRepository {


    override fun getAllCharactersPagingSource(): PagingSource<Int, GetAllCharactersQuery.Result> {
        return CharactersResultPagingSource(apolloClient, networkMonitor)
    }

    override suspend fun getSingleCharacter(characterId: String): Flow<ApolloResponse<GetSingleCharacterQuery.Data>> {
        return (apolloClient.query(GetSingleCharacterQuery(characterId)).toFlow())
    }
}