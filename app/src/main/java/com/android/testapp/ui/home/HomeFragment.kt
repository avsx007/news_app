package com.android.testapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.testapp.R
import com.android.testapp.base.BaseFragment
import com.android.testapp.model.*
import com.android.testapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home_.*


class HomeFragment : BaseFragment<HomeViewModel>(), ArticleAdapter.ArticleClickListener {
    var adapter: ArticleAdapter? = null
    var articles = ArrayList<ArticleBody>()

    override fun layoutRes(): Int {
        return R.layout.fragment_home_
    }

    override fun getViewModelType(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLoading().observe(viewLifecycleOwner, Observer {
            if(it){
                pbArticle.visibility = View.VISIBLE
                rvArticles.visibility = View.GONE
            }
        })
        viewModel.getError().observe(viewLifecycleOwner, Observer {
            swiperefreshlayout.isRefreshing = false
            pbArticle.visibility = View.GONE
            rvArticles.visibility = View.VISIBLE

        })

        viewModel.getArticlesObserver().observe(viewLifecycleOwner, Observer {
            setPostView(it)
        })

        swiperefreshlayout.setOnRefreshListener {
            getArticles()
        }

        adapter = ArticleAdapter(ArrayList(),requireContext(), this)
        rvArticles.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvArticles.adapter = adapter
        getArticles()
    }

    private fun setPostView(it: List<ArticleBody>) {
        articles.addAll(it)
        adapter?.swapData(articles)
        swiperefreshlayout.isRefreshing = false
        pbArticle.visibility = View.GONE
        rvArticles.visibility = View.VISIBLE
    }


    private fun getArticles() {
        viewModel.getArticles()
    }

    override fun onArticleClicked(body: ArticleBody) {
        Navigation.findNavController(requireView()).navigate(HomeFragmentDirections.actionHomeToArticle(body))
    }
}
