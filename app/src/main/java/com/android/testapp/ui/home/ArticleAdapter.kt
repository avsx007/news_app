package com.android.testapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.android.testapp.R
import com.android.testapp.model.ArticleBody

class ArticleAdapter(
    var articles: ArrayList<ArticleBody>,
    val mContext: Context,
    val listener: ArticleClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_DEFAULT = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            else -> {
                return ArticleViewHolder(
                    LayoutInflater.from(mContext).inflate(
                        R.layout.item_article,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_DEFAULT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_DEFAULT -> {
                (holder as ArticleViewHolder).loadData()
            }
        }
    }

    fun swapData(articles: List<ArticleBody>) {
        this.articles = ArrayList(articles)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var ivArticle:ImageView = itemView.findViewById(R.id.ivArticle)
        var tvTitle:TextView = itemView.findViewById(R.id.tvTitle)
        var tvPublishedAt:TextView = itemView.findViewById(R.id.tvPublishedAt)
        fun loadData() {
            with(articles[adapterPosition]) {
                Glide.with(mContext).load(urlToImage).into(ivArticle)
                tvTitle.text = title
                tvPublishedAt.text = publishedAt
            }
            itemView.setOnClickListener {
                listener.onArticleClicked(articles[adapterPosition])
            }
        }
    }

    interface ArticleClickListener{
        fun onArticleClicked(body: ArticleBody)
    }
}