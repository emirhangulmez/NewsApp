package com.emirhangulmez.newsapp.di

import com.emirhangulmez.newsapp.data.repository.NewsRepositoryImpl
import com.emirhangulmez.newsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}