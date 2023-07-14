package com.emirhangulmez.newsapp.news

import com.emirhangulmez.newsapp.domain.entity.ArticleEntity

object NewsTestDataFactory {
    val articleEntityList = listOf(
        ArticleEntity(
            title = "Technology News 1",
            description = "Learn about the latest technology news 1.",
            sourceName = "Tech News Outlet 1",
            urlToImage = "https://example.com/image4.jpg",
            url = "https://example.com/article4"
        ),
        ArticleEntity(
            title = "Technology News 2",
            description = "Learn about the latest technology news 2.",
            sourceName = "Tech News Outlet 2",
            urlToImage = "https://example.com/image5.jpg",
            url = "https://example.com/article5"
        )
    )
}