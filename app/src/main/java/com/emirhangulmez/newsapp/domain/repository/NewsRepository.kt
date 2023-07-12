package com.emirhangulmez.newsapp.domain.repository

import com.emirhangulmez.newsapp.common.Resource
import com.emirhangulmez.newsapp.data.remote.dto.GetTopHeadlinesResponse
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(): Flow<Resource<List<ArticleEntity>>>
}