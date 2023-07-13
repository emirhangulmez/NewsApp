package com.emirhangulmez.newsapp.di

import com.emirhangulmez.newsapp.domain.usecase.GetTopHeadlinesUseCase
import com.emirhangulmez.newsapp.domain.usecase.GetTopHeadlinesUseCaseImpl
import com.emirhangulmez.newsapp.domain.usecase.TopHeadlinesPagerUseCase
import com.emirhangulmez.newsapp.domain.usecase.TopHeadlinesPagerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetTopHeadlinesUseCase(getTopHeadlinesUseCaseImpl: GetTopHeadlinesUseCaseImpl): GetTopHeadlinesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindTopHeadlinesPagerUseCase(topHeadlinesPagerUseCaseImpl: TopHeadlinesPagerUseCaseImpl): TopHeadlinesPagerUseCase
}