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
import com.example.challenge2.adapter.CovidCountryAdapter
import com.example.challenge2.api.CountryService
import com.example.challenge2.api.apiRequest
import com.example.challenge2.api.httpClient
import com.example.challenge2.item.CovidCountryItem
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_country.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetCovidCountry()
    }
    private fun callApiGetCovidCountry() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<CountryService>(httpClient, "https://api.kawalcorona.com")
        val call = apiRequest.getCountry()
        call.enqueue(object : Callback<List<CovidCountryItem>> {
            override fun onFailure(call: Call<List<CovidCountryItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<CovidCountryItem>>, response:
                Response<List<CovidCountryItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilCovidCountry(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }
    private fun tampilCovidCountry(covCou: List<CovidCountryItem>) {
        listCovidCountry.layoutManager = LinearLayoutManager(context)
        listCovidCountry.adapter = CovidCountryAdapter(context!!, covCou) {
            val covidCountry = it
            tampilToast(context!!, covidCountry.attributes.countryRegion)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
