package com.sagr.rickandmortygraphql.presentation.characterdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class CharacterDetailsCoordinator(
    private val characterId: String,
    val viewModel: CharacterDetailsViewModel
) {
    val screenStateFlow = viewModel.stateFlow


    fun getCharacterDetails() {
        viewModel.getCharacterDetails(characterId)
    }
}

@Composable
fun rememberCharacterDetailsCoordinator(
    characterId: String,
    viewModel: CharacterDetailsViewModel = hiltViewModel()
): CharacterDetailsCoordinator {
    return remember(viewModel, characterId) {
        CharacterDetailsCoordinator(
            characterId = characterId,
            viewModel = viewModel
        )
    }
}