package com.sagr.rickandmortygraphql.presentation.characterdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun CharacterDetailsRoute(
    characterId: String
) {
    val coordinator: CharacterDetailsCoordinator =
        rememberCharacterDetailsCoordinator(characterId = characterId)

    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(CharacterDetailsState())

    // UI Actions
    val actions = rememberCharacterDetailsActions(coordinator, characterId)

    // UI Rendering
    CharacterDetailsScreen(uiState, actions)
}


@Composable
fun rememberCharacterDetailsActions(
    coordinator: CharacterDetailsCoordinator,
    characterId: String
): CharacterDetailsActions {
    return remember(coordinator, characterId) {
        CharacterDetailsActions(
            getCharacterDetails = { coordinator.getCharacterDetails() },
            characterId = characterId
        )
    }
}