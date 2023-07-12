package com.emirhangulmez.newsapp.data.repository

import com.emirhangulmez.newsapp.common.Extensions.getOrWriteNull
import com.emirhangulmez.newsapp.common.Resource
import com.emirhangulmez.newsapp.data.mapper.ArticleEntityMapper
import com.emirhangulmez.newsapp.data.remote.NewsService
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import com.emirhangulmez.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val client: NewsService,
    private val articleEntityMapper: ArticleEntityMapper
) : NewsRepository {

    override suspend fun getTopHeadlines(): Flow<Resource<List<ArticleEntity>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(articleEntityMapper.map(client.getTopHeadlines().articles)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message.getOrWriteNull()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.getOrWriteNull()))
        }
    }

}