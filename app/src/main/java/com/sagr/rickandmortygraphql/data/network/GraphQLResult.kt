package com.sagr.rickandmortygraphql.data.network

sealed class GraphQLResult<out T> {
    data class Success<T>(val data: T) : GraphQLResult<T>()
    object Loading : GraphQLResult<Nothing>()
    data class Error(val message: String) : GraphQLResult<Nothing>()
}