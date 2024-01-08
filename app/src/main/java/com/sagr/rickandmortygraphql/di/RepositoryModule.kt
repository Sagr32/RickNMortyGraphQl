package com.sagr.rickandmortygraphql.di

import com.sagr.rickandmortygraphql.data.repository.MainRepositoryImpl
import com.sagr.rickandmortygraphql.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindAuthorizationRepository(repository: MainRepositoryImpl): MainRepository

}