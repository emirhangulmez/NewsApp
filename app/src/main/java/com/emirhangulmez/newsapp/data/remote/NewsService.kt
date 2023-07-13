package com.emirhangulmez.newsapp.data.remote

import com.emirhangulmez.newsapp.common.Constants.API_KEY_QUERY_NAME
import com.emirhangulmez.newsapp.common.Constants.API_KEY_QUERY_VALUE
import com.emirhangulmez.newsapp.common.Constants.COUNTRY_QUERY_NAME
import com.emirhangulmez.newsapp.common.Constants.COUNTRY_QUERY_VALUE
import com.emirhangulmez.newsapp.common.Constants.PAGE_QUERY_NAME
import com.emirhangulmez.newsapp.common.Constants.PAGE_SIZE_QUERY_NAME
import com.emirhangulmez.newsapp.common.Constants.PAGE_SIZE_QUERY_VALUE
import com.emirhangulmez.newsapp.common.Constants.TOP_HEADLINES_ENDPOINT
import com.emirhangulmez.newsapp.data.remote.dto.GetTopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(TOP_HEADLINES_ENDPOINT)
    suspend fun getTopHeadlines(
        @Query(COUNTRY_QUERY_NAME) country: String = COUNTRY_QUERY_VALUE,
        @Query(API_KEY_QUERY_NAME) apiKey: String = API_KEY_QUERY_VALUE,
        @Query(PAGE_SIZE_QUERY_NAME) pageSize: Int = PAGE_SIZE_QUERY_VALUE,
        @Query(PAGE_QUERY_NAME) page: Int
    ): GetTopHeadlinesResponse
}