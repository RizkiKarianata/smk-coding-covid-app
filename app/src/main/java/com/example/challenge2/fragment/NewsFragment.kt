package com.example.challenge2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.challenge2.R
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.adapter.NewsAdapter
import com.example.challenge2.api.NewsService
import com.example.challenge2.api.apiRequest
import com.example.challenge2.api.httpClient
import com.example.challenge2.item.Article
import com.example.challenge2.item.NewsArticle
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetNews()
    }
    private fun callApiGetNews() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<NewsService>(httpClient, "http://newsapi.org/")
        val call = apiRequest.getNews("id", "health", "bbee86a986db46bf80ff438b2af16712")
        call.enqueue(object : Callback<NewsArticle> {
            override fun onFailure(call: Call<NewsArticle>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<NewsArticle>, response:
                Response<NewsArticle>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful -> {
                        tampilNews(response.body()!!.articles)
                    }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }
    private fun tampilNews(newsArt: List<Article>) {
        listNews.layoutManager = LinearLayoutManager(context)
        listNews.adapter = NewsAdapter(context!!, newsArt) {
            val newsArticle = it
            tampilToast(context!!, newsArticle.title)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
