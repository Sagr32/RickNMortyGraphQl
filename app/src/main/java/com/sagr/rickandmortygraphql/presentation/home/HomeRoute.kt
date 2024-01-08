package com.sagr.rickandmortygraphql.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun HomeRoute(
    navController: NavController
) {
    val coordinator: HomeCoordinator = rememberHomeCoordinator(navController = navController)

    // State observing and declarations
    val uiState by coordinator.homeState

    // UI Actions
    val actions = rememberHomeActions(coordinator)

    // UI Rendering
    HomeScreen(uiState, actions)
}


@Composable
fun rememberHomeActions(coordinator: HomeCoordinator): HomeActions {
    return remember(coordinator) {
        HomeActions(
            fetchCharacters = coordinator::fetchCharacters,
            navigateToDetails = coordinator::navigateToDetails
        )
    }
}