package com.android.testapp.data.webservice

import com.android.testapp.model.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface RestService {
    @GET("top-headlines?country=us&category=business&apiKey=fd78102346e84058b2e52185ca2c9606")
    suspend fun getArticles(): Response<ArticleHolder>

}