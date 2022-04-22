package com.android.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.testapp.data.repository.DataRepository
import com.android.testapp.model.ArticleBody
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val dataRepository: DataRepository
) : ViewModel() {
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    private var repoLoading: MutableLiveData<Boolean> = MutableLiveData()
    private var repoLoadError: MutableLiveData<String> = MutableLiveData()
    private var articlesMutable: MutableLiveData<List<ArticleBody>> = MutableLiveData()

    fun getError(): LiveData<String> {
        return repoLoadError
    }

    fun getLoading(): LiveData<Boolean> {
        return repoLoading
    }

    fun getArticlesObserver(): LiveData<List<ArticleBody>> {
        return articlesMutable
    }

    fun getArticles() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = dataRepository.getArticles()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    articlesMutable.value = response.body()?.articles
                } else {
                    repoLoadError.value = response.message()
                }
                repoLoading.value = false
            }
        }
    }

    public override fun onCleared() {
        compositeDisposable?.dispose()
        super.onCleared()
    }

}