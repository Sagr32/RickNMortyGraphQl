package com.sagr.rickandmortygraphql.data.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.ApolloClient
import com.sagr.rickandmortygraphql.GetAllCharactersQuery
import com.sagr.rickandmortygraphql.data.network.NetworkMonitor
import javax.inject.Inject

class CharactersResultPagingSource @Inject constructor(
    private val apolloClient: ApolloClient,
    private val networkMonitor: NetworkMonitor
) :
    PagingSource<Int, GetAllCharactersQuery.Result>() { // Replace Character with your actual data model

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetAllCharactersQuery.Result> {
        val pageNumber = params.key ?: STARTING_PAGE_INDEX
        if (!networkMonitor.isNetworkAvailable()) {
            return LoadResult.Error(Exception("No internet connection"))
        }
        try {
            val response = apolloClient.query(GetAllCharactersQuery(page = pageNumber)).execute()
            val data = response.data?.characters?.results?.filterNotNull() ?: emptyList()


            val prevKey = if (pageNumber > STARTING_PAGE_INDEX) pageNumber - 1 else null
            val nextKey = if (data.isNotEmpty()) pageNumber + 1 else null

            return LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GetAllCharactersQuery.Result>): Int? {
        val anchorPosition: Int = state.anchorPosition ?: return null
        val (_, prevKey, nextKey) = state.closestPageToPosition(anchorPosition)
            ?: return null
        if (prevKey != null) {
            return prevKey + 1
        }
        return if (nextKey != null) {
            nextKey - 1
        } else null
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}