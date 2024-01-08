package com.sagr.rickandmortygraphql.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.sagr.rickandmortygraphql.GetAllCharactersQuery
import com.sagr.rickandmortygraphql.Graph
import com.sagr.rickandmortygraphql.R
import com.sagr.rickandmortygraphql.presentation.sharedComponents.LoadingBox


@Composable
fun CharacterGridList(
    pagingItems: LazyPagingItems<GetAllCharactersQuery.Result>,
    onCharacterClick: (String) -> Unit
) {
    when (pagingItems.loadState.refresh) {

        LoadState.Loading -> {
            LoadingBox()
        }

        is LoadState.NotLoading -> {
            if (pagingItems.itemCount == 0) {
                Text(text = stringResource(id = R.string.app_name))
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(pagingItems.itemCount) { index ->
                        pagingItems[index]?.let { character ->
                            CharacterGridCard(character = character, onClick = { id ->
                                onCharacterClick(id)
                            })
                        }
                    }
                }
            }
        }

        else -> {
            val errorState = when {
                pagingItems.loadState.refresh is LoadState.Error -> pagingItems.loadState.refresh as LoadState.Error
                pagingItems.loadState.append is LoadState.Error -> pagingItems.loadState.append as LoadState.Error
                pagingItems.loadState.prepend is LoadState.Error -> pagingItems.loadState.prepend as LoadState.Error
                else -> null
            }

            if (errorState != null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Error: ${errorState.error.localizedMessage}",
                        color = Color.Red
                    )
                    Button(onClick = { pagingItems.retry() }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}