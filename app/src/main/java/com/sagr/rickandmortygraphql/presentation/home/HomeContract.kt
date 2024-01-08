package com.sagr.rickandmortygraphql.presentation.home

import androidx.paging.PagingData
import com.sagr.rickandmortygraphql.GetAllCharactersQuery
import com.sagr.rickandmortygraphql.data.network.GraphQLResult
import kotlinx.coroutines.flow.Flow


/**
 * UI State that represents HomeScreen
 **/
class HomeState(
    val characterList: Flow<PagingData<GetAllCharactersQuery.Result>>? = null
)

/**
 * Home Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class HomeActions(
    val fetchCharacters: () -> Unit = {},
    val navigateToDetails: (String) -> Unit = {}
)