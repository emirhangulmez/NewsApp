package com.emirhangulmez.newsapp.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import com.emirhangulmez.newsapp.domain.usecase.TopHeadlinesPagerUseCase
import com.emirhangulmez.newsapp.news.NewsTestDataFactory.articleEntityList
import com.emirhangulmez.newsapp.presentation.news.NewsViewModel
import com.emirhangulmez.newsapp.presentation.news.adapter.NewsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest {
    private lateinit var viewModel: NewsViewModel

    @Mock
    private lateinit var topHeadlinesPagerUseCase: TopHeadlinesPagerUseCase

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = NewsViewModel(
            topHeadlinesPagerUseCase
        )
    }

    class TestListCallback : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }

    @Test
    fun whenTopHeadlinesPagerUseCaseStateIsData_pagingDataReturned() = runTest {

        val testPagingData = PagingData.from(articleEntityList)
        whenever(topHeadlinesPagerUseCase.invoke()).thenReturn(flow { emit(testPagingData) })

        val differ = AsyncPagingDataDiffer(
            diffCallback = NewsAdapter.ArticleDiff, updateCallback = TestListCallback(),
        )

        viewModel.getTopHeadlines(null)
        viewModel.topHeadlines.asFlow().collect { pagingData ->
            differ.submitData(pagingData)
            Assert.assertEquals(articleEntityList, differ.snapshot().items)
        }
    }
}