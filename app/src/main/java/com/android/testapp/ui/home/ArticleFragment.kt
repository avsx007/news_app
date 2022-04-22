package com.android.testapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.android.testapp.R
import com.android.testapp.base.BaseFragment
import com.android.testapp.model.*
import com.android.testapp.viewmodel.HomeViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_article.*


class ArticleFragment : BaseFragment<HomeViewModel>(){
    val args: ArticleFragmentArgs by navArgs()
    override fun layoutRes(): Int {
        return R.layout.fragment_article
    }

    override fun getViewModelType(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(args.articleBody) {
            Glide.with(requireContext()).load(urlToImage).into(ivArticle)
            tvTitle.text = title
            tvDescription.text = "$description \n\n $content"
            tvPublishedAt.text = publishedAt
        }

        ivShare.setOnClickListener {
            with(args.articleBody) {
                shareTextOnly("$description \n\n $url")
            }
        }
    }

    private fun shareTextOnly(text: String) {
        val intentt = Intent(Intent.ACTION_SEND)
        intentt.type = "text/plain"
        intentt.putExtra(Intent.EXTRA_SUBJECT, "Share this news")
        intentt.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intentt, "Share Via"))
    }
}
