package com.sagr.rickandmortygraphql.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.sagr.rickandmortygraphql.presentation.home.components.CharacterGridList

@Composable
fun HomeScreen(
    state: HomeState?,
    actions: HomeActions,
) {
    val charactersFlow = state?.characterList?.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true, block = {
        actions.fetchCharacters()
    })
    charactersFlow?.let {
        CharacterGridList(pagingItems = it, onCharacterClick = { characterId ->
            actions.navigateToDetails(characterId)
        })
    }
}