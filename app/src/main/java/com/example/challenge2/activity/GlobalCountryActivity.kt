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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.adapter.CovidGlobalAdapter
import com.example.challenge2.api.GlobalService
import com.example.challenge2.api.apiRequest
import com.example.challenge2.api.httpClient
import com.example.challenge2.item.GlobalCovidItem
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.main.activity_global_country.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.Window
import android.view.WindowManager

class GlobalCountryActivity : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    super.onCreate(savedInstanceState)
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
    setContentView(R.layout.activity_global_country)
    SessionCountry.Session(this)
    callGlobalCovid()
}
    private fun callGlobalCovid() {
        showLoading(this, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<GlobalService>(httpClient, "https://covid19.mathdro.id")
        val call = apiRequest.getGlobal()
        call.enqueue(object : Callback<List<GlobalCovidItem>> {
            override fun onFailure(call: Call<List<GlobalCovidItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<GlobalCovidItem>>, response:
                Response<List<GlobalCovidItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilGlobal(response.body()!!)
                            else -> {
                            }
                        }
                    else -> {
                    }
                }
            }
        })
    }
    private fun tampilGlobal(covCou: List<GlobalCovidItem>) {
        listGlobalCountry.layoutManager = LinearLayoutManager(this)
        listGlobalCountry.adapter = CovidGlobalAdapter(this, covCou) {
            val covidGlobal = it
            SessionCountry.Session(this)
            SessionCountry["country"] = covidGlobal.countryRegion
            val intent = Intent(this, DetailGlobalActivity::class.java)
            startActivity(intent)
            tampilToast(this, covidGlobal.countryRegion)
        }
    }
}
