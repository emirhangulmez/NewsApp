package com.emirhangulmez.newsapp.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import com.emirhangulmez.newsapp.domain.paging.TopHeadlinesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadlinesPagerUseCaseImpl @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : TopHeadlinesPagerUseCase {
    override fun invoke(): Flow<PagingData<ArticleEntity>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            initialLoadSize = 10,
            prefetchDistance = 5
        ),
        pagingSourceFactory = {
            TopHeadlinesPagingSource(getTopHeadlinesUseCase)
        }
    ).flow

}