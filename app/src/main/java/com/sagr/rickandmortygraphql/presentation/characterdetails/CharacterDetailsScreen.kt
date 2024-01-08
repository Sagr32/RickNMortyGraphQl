package com.sagr.rickandmortygraphql.presentation.characterdetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CharacterDetailsScreen(
    state: CharacterDetailsState,
    actions: CharacterDetailsActions,
) {
    actions.getCharacterDetails()



}

@Composable
@Preview(name = "CharacterDetails")
private fun CharacterDetailsScreenPreview() {
    CharacterDetailsScreen(
        state = CharacterDetailsState(),
        actions = CharacterDetailsActions(),
    )
}

