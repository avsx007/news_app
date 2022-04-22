package com.android.testapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ArticleHolder (
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null,

    @SerializedName("articles")
    @Expose
    var articles: List<ArticleBody>? = null
)