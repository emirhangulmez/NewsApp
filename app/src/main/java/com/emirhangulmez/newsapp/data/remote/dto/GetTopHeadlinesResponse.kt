package com.emirhangulmez.newsapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GetTopHeadlinesResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)