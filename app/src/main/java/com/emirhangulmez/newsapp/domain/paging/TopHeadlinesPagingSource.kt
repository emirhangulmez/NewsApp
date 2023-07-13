package com.emirhangulmez.newsapp.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.emirhangulmez.newsapp.common.Resource
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import com.emirhangulmez.newsapp.domain.usecase.GetTopHeadlinesUseCase
import java.sql.Types.NULL

class TopHeadlinesPagingSource(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : PagingSource<Int, ArticleEntity>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPageIndex = state.pages.indexOf(state.closestPageToPosition(anchorPosition))
            state.pages.getOrNull(anchorPageIndex + 1)?.prevKey ?: state.pages.getOrNull(
                anchorPageIndex - 1
            )?.nextKey
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleEntity> {
        val pageIndex = params.key ?: 1
        var loadResult: LoadResult<Int, ArticleEntity>? = null
        getTopHeadlinesUseCase(pageIndex).collect { result ->
            when (result) {
                is Resource.Success -> {
                    val nextKey = if (result.data.isNullOrEmpty()) {
                        null
                    } else {
                        pageIndex.plus(1)
                    }

                    loadResult = LoadResult.Page(
                        data = result.data ?: emptyList(),
                        prevKey = null,
                        nextKey = nextKey
                    )
                }

                is Resource.Error -> {
                    loadResult = LoadResult.Error(Throwable(message = result.message))
                }

                is Resource.Loading -> {}
            }
        }
        return loadResult ?: LoadResult.Error(Throwable(message = NULL.toString()))
    }

}