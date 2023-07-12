package com.emirhangulmez.newsapp.di

import com.emirhangulmez.newsapp.data.mapper.ArticleEntityMapper
import com.emirhangulmez.newsapp.data.remote.dto.Article
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import com.emirhangulmez.newsapp.domain.mapper.NewsListMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindArticleEntityMapper(articleEntityMapper: ArticleEntityMapper): NewsListMapper<Article, ArticleEntity>
}