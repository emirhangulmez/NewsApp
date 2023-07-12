package com.emirhangulmez.newsapp.domain.mapper

interface NewsBaseMapper<I, O> {
    fun map(input: I): O
}