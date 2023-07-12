package com.emirhangulmez.newsapp.data.mapper

import com.emirhangulmez.newsapp.data.remote.dto.Article
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import com.emirhangulmez.newsapp.domain.mapper.NewsListMapper
import javax.inject.Inject

class ArticleEntityMapper @Inject constructor() : NewsListMapper<Article, ArticleEntity> {
    override fun map(input: List<Article>): List<ArticleEntity> {
        return input.map {
            ArticleEntity(
                it.title,
                it.description.orEmpty(),
                it.source.name,
                it.urlToImage.orEmpty(),
                it.url
            )
        }
    }

}