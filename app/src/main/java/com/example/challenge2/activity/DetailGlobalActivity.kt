package com.example.challenge2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge2.R
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.challenge2.session.SessionCountry
import android.content.Intent
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.adapter.DetailGlobalAdapter
import com.example.challenge2.api.DetailGlobalService
import com.example.challenge2.api.apiRequest
import com.example.challenge2.api.httpClient
import com.example.challenge2.item.DetailGlobalCovidItem
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.main.activity_detail_global.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.Window
import android.view.WindowManager

class DetailGlobalActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_detail_global)
        SessionCountry.Session(this)
        callGlobalCovid()
        tampilGambar()
    }
    private fun callGlobalCovid() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<DetailGlobalService>(httpClient, "https://covid19.mathdro.id")
        val data = SessionCountry["country"]
        val call = apiRequest.getDetailGlobal(data!!)
        call.enqueue(object : Callback<List<DetailGlobalCovidItem>> {
            override fun onFailure(call: Call<List<DetailGlobalCovidItem>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<DetailGlobalCovidItem>>, response:
                Response<List<DetailGlobalCovidItem>>
            ) {
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilDetailGlobal(response.body()!!)
                            else -> {
                            }
                        }
                    else -> {
                    }
                }
            }
        })
    }
    private fun tampilDetailGlobal(covCou: List<DetailGlobalCovidItem>) {
        listGlobalDetail.layoutManager = LinearLayoutManager(this)
        listGlobalDetail.adapter = DetailGlobalAdapter(this, covCou) {
            val covidGlobal = it
            tampilToast(this, covidGlobal.countryRegion)
        }
    }
    private fun tampilGambar() {
        val width = DisplayMetrics().widthPixels
        val h = (width / 1.9).toInt()
        Glide.with(this)
            .load("https://covid19.mathdro.id/api/countries/${SessionCountry["country"]}/og")
            .apply(RequestOptions().override(width, h))
            .into(txtImageGlobal)
    }
}
