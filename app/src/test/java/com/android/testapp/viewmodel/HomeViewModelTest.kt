package com.android.testapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.testapp.RxImmediateSchedulerRule
import com.android.testapp.data.repository.DataRepository
import com.android.testapp.data.webservice.RestService
import com.android.testapp.model.ArticleBody
import com.android.testapp.model.ArticleHolder
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : TestCase() {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    lateinit var articleAPI: RestService

    @Mock
    lateinit var observer: Observer<List<ArticleBody>>

    private lateinit var viewmodel : HomeViewModel

    @Before
    fun setup() {
        viewmodel = HomeViewModel(DataRepository(articleAPI))
    }

    @Test
    fun testResponseData() {
        // -- mock data
        val article = ArticleBody("ArticleTitle",
            "UrlDummy")
        val articleList = ArrayList<ArticleBody>()
        articleList.add(article)
        val artHolder = ArticleHolder("ok",1, articleList)
        //---

        Mockito.`when`(articleAPI.getArticles())
            .thenReturn(Single.just(Response.success(artHolder)))

        viewmodel.getArticlesObserver().observeForever(observer)
        viewmodel.getArticles()

        assert(viewmodel.getArticlesObserver().value!![0].title.equals(article.title))
        assert(viewmodel.getArticlesObserver().value!![0].urlToImage.equals(article.urlToImage))
    }
}