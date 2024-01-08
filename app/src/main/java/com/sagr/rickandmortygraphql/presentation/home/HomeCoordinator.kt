package com.sagr.rickandmortygraphql.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sagr.rickandmortygraphql.Graph

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class HomeCoordinator(
    val viewModel: HomeViewModel,
    private val navController: NavController

) {
    val homeState = viewModel.homeState

    fun fetchCharacters() {
        viewModel.fetchAllCharacters()
    }

    fun navigateToDetails(characterId: String) {
        navController.navigate("${Graph.DETAILS}/$characterId")
    }
}

@Composable
fun rememberHomeCoordinator(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
): HomeCoordinator {
    return remember(viewModel) {
        HomeCoordinator(
            viewModel = viewModel,
            navController
        )
    }
}