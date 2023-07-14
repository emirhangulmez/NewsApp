package com.emirhangulmez.newsapp.common

import androidx.paging.PagingData
import app.cash.turbine.test
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert

object FlowTestUtils {

    fun testStateAfterUseCaseState(
        flowToBeCollected: Flow<PagingData<ArticleEntity>>,
        launchService: suspend Job.() -> Unit,
        extras: (() -> Unit)? = null,
        assertEquals: (PagingData<ArticleEntity>) -> Pair<PagingData<ArticleEntity>, PagingData<ArticleEntity>>
    ) = runTest {
        launch {
            flowToBeCollected.test {
                val states = assertEquals.invoke(awaitItem())
                Assert.assertEquals(states.first, states.second)
                cancelAndConsumeRemainingEvents()
            }
        }.apply {
            extras?.invoke()
            launchService.invoke(this)
            join()
            cancel()
        }
    }
}


