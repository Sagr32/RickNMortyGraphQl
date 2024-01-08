package com.sagr.rickandmortygraphql.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sagr.rickandmortygraphql.GetAllCharactersQuery
import com.sagr.rickandmortygraphql.data.network.GraphQLResult
import com.sagr.rickandmortygraphql.data.network.NetworkMonitor
import com.sagr.rickandmortygraphql.domain.usecase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
) : ViewModel() {


    var homeState = mutableStateOf<HomeState?>(null)
        private set

    fun fetchAllCharacters() {
        viewModelScope.launch {
            homeState.value = HomeState(characterList = Pager(
                config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                pagingSourceFactory = { getAllCharactersUseCase() }
            ).flow.cachedIn(viewModelScope))
        }
    }


}