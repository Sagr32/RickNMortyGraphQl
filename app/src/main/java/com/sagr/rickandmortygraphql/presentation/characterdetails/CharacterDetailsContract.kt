package com.sagr.rickandmortygraphql.presentation.characterdetails

import com.sagr.rickandmortygraphql.GetSingleCharacterQuery
import com.sagr.rickandmortygraphql.data.network.GraphQLResult


/**
 * UI State that represents CharacterDetailsScreen
 **/
class CharacterDetailsState(
    val result: GraphQLResult<GetSingleCharacterQuery.Character>? = null
)

/**
 * CharacterDetails Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class CharacterDetailsActions(
    val getCharacterDetails: () -> Unit = {},
    val characterId: String = ""

)