package com.emirhangulmez.newsapp.domain.usecase

import androidx.paging.PagingData
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface TopHeadlinesPagerUseCase {
    operator fun invoke(): Flow<PagingData<ArticleEntity>>
}