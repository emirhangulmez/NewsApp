package com.emirhangulmez.newsapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsArg(
    val url: String
) : Parcelable