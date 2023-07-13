package com.emirhangulmez.newsapp.common

import com.emirhangulmez.newsapp.BuildConfig

object Constants {

    const val BASE_URL = "https://newsapi.org/"
    const val TOP_HEADLINES_ENDPOINT = "v2/top-headlines"

    const val COUNTRY_QUERY_NAME = "country"
    const val COUNTRY_QUERY_VALUE = "tr"

    const val API_KEY_QUERY_NAME = "apiKey"
    const val API_KEY_QUERY_VALUE = BuildConfig.API_KEY

    const val PAGE_SIZE_QUERY_NAME = "pageSize"
    const val PAGE_SIZE_QUERY_VALUE = 10

    const val PAGE_QUERY_NAME = "page"
}