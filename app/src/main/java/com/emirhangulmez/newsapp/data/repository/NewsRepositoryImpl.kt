package com.emirhangulmez.newsapp.data.repository

import com.emirhangulmez.newsapp.common.Extensions.getOrWriteNull
import com.emirhangulmez.newsapp.common.Resource
import com.emirhangulmez.newsapp.data.remote.NewsService
import com.emirhangulmez.newsapp.data.remote.dto.GetTopHeadlinesResponse
import com.emirhangulmez.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val client: NewsService
) : NewsRepository {

    override suspend fun getTopHeadlines(): Flow<Resource<GetTopHeadlinesResponse>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(client.getTopHeadlines()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message.getOrWriteNull()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.getOrWriteNull()))
        }
    }

}