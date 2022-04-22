package com.android.testapp.data.repository

import com.android.testapp.data.webservice.RestService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(val restService: RestService){
    suspend fun getArticles() = restService.getArticles()
}