package com.example.challenge2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.challenge2.R
import com.example.challenge2.item.Article
import com.example.challenge2.session.SessionCountry
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_item.*
import java.util.*

class ArticleAdapter(private val context : Context, private val items :
List<Article>, private val listener : (Article)-> Unit) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.article_item,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }
    class ViewHolder(val context : Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{
        @SuppressLint("SetTextI18n")
        fun bindItem(item: Article, listener: (Article) -> Unit) {
            Glide.with(context).load(item.urlToImage).into(txtArticleImage)
            txtAuthor.text = item.author
            txtDescription.text = item.description
            txtSource.text = item.source.name
            txtTitle.text = item.title
            txtPublishedAt.text = item.publishedAt
            containerView.setOnClickListener { listener(item) }
        }
    }
}