package com.example.challenge2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.challenge2.R
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.adapter.ArticleAdapter
import com.example.challenge2.api.ArticleService
import com.example.challenge2.api.apiRequest
import com.example.challenge2.api.httpClient
import com.example.challenge2.item.Article
import com.example.challenge2.item.NewsArticle
import com.example.challenge2.session.SessionCountry
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_article.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetArticle()
    }
    private fun callApiGetArticle() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiNewsRequest = apiRequest<ArticleService>(httpClient, "https://newsapi.org")
        val call = apiNewsRequest.getArticle()
        call.enqueue(object : Callback<NewsArticle> {
            override fun onFailure(call: Call<NewsArticle>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
                tampilToast(context!!, "Gagal" + t.message)
            }

            override fun onResponse(
                call: Call<NewsArticle>, response:
                Response<NewsArticle>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful -> {
                        tampilArticle(response.body()!!.articles)
                    }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }
    private fun tampilArticle(covCou: List<Article>) {
        listArticleNewss.layoutManager = LinearLayoutManager(context)
        listArticleNewss.adapter = ArticleAdapter(context!!, covCou) {
            SessionCountry.Session(context)
            val newsArticle = it
            tampilToast(context!!, newsArticle.title)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
