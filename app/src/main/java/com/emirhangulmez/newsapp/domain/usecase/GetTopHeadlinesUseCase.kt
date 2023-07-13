package com.emirhangulmez.newsapp.domain.usecase

import com.emirhangulmez.newsapp.common.Resource
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface GetTopHeadlinesUseCase {
    operator fun invoke(page: Int): Flow<Resource<List<ArticleEntity>>>
}