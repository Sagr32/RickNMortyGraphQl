package com.sagr.rickandmortygraphql

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sagr.rickandmortygraphql.presentation.characterdetails.CharacterDetailsRoute
import com.sagr.rickandmortygraphql.presentation.home.HomeRoute

@Composable
fun MainNavigation(
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Graph.Home) {
        composable(
            route = Graph.Home
        ) {
            HomeRoute(navController)
        }
        composable(
            route = "${Graph.DETAILS}/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.StringType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")
            if (characterId != null) {
                CharacterDetailsRoute(
                    characterId = characterId
                )
            } else {
                // Handle the case when characterId is null
            }
        }
    }
}



object Graph {
    const val Home = "home_screen"
    const val DETAILS = "details_screen"
    const val PROFILE = "profile_graph"
    const val ORDER = "order"
}