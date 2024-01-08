package com.sagr.rickandmortygraphql.presentation.characterdetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sagr.rickandmortygraphql.GetSingleCharacterQuery
import com.sagr.rickandmortygraphql.data.network.GraphQLResult
import com.sagr.rickandmortygraphql.data.network.NetworkMonitor
import com.sagr.rickandmortygraphql.domain.usecase.GetCharacterDetailsUseCase
import com.sagr.rickandmortygraphql.presentation.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<CharacterDetailsState> =
        MutableStateFlow(CharacterDetailsState())

    val stateFlow: StateFlow<CharacterDetailsState> = _stateFlow.asStateFlow()

    fun getCharacterDetails(id: String) {
        Log.e("TAG", "getCharacterDetails: $id")
        viewModelScope.launch {
            try {
                if (!networkMonitor.isNetworkAvailable()) {
                    _stateFlow.emit(CharacterDetailsState(GraphQLResult.Error("No internet connection. Please check your network settings.")))
                    return@launch
                }
                _stateFlow.emit(CharacterDetailsState(GraphQLResult.Loading))
                getCharacterDetailsUseCase(id).collect { result ->
                    val character = result.data?.character
                    if (character == null) {
                        _stateFlow.emit(
                            CharacterDetailsState(
                                GraphQLResult.Error("Character details not found.")
                            )
                        )
                    } else {
                        _stateFlow.emit(
                            CharacterDetailsState(
                                result = GraphQLResult.Success(character)
                            )
                        )
                    }
                }

            } catch (e: Exception) {
                _stateFlow.emit(
                    CharacterDetailsState(
                        GraphQLResult.Error("An error occurred: ${e.message}")
                    )
                )

            }
        }
    }

}

