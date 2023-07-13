package com.emirhangulmez.newsapp.domain.repository

import com.emirhangulmez.newsapp.common.Resource
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getTopHeadlines(page: Int): Flow<Resource<List<ArticleEntity>>>
}