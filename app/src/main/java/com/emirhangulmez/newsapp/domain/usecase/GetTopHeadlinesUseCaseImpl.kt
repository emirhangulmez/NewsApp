package com.emirhangulmez.newsapp.domain.usecase

import com.emirhangulmez.newsapp.common.Resource
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import com.emirhangulmez.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopHeadlinesUseCaseImpl @Inject constructor(
    private val repo: NewsRepository
) : GetTopHeadlinesUseCase {

    override fun invoke(page: Int): Flow<Resource<List<ArticleEntity>>> =
        repo.getTopHeadlines(page)

}