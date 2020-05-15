package com.example.challenge2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.kawalcorona.ProvincesService
import com.example.challenge2.kawalcorona.apiRequest
import com.example.challenge2.kawalcorona.httpClient
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_provinces.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvincesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_provinces, container, false )
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetCovidProvinces()
    }
    private fun callApiGetCovidProvinces() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<ProvincesService>(httpClient)
        val call = apiRequest.getProvinces()
        call.enqueue(object : Callback<List<CovidProvincesItem>> {
            override fun onFailure(call: Call<List<CovidProvincesItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<CovidProvincesItem>>, response:
                Response<List<CovidProvincesItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilCovidProvinces(response.body()!!)
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
    private fun tampilCovidProvinces(covProv: List<CovidProvincesItem>) {
        listCovidProvinces.layoutManager = LinearLayoutManager(context)
        listCovidProvinces.adapter = CovidProvincesAdapter(context!!, covProv) {
            val covidProvinces = it
            tampilToast(context!!, covidProvinces.attributes.provinsi)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
