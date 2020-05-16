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
import com.example.challenge2.skuydev.HospitalService
import com.example.challenge2.skuydev.apiRequest
import com.example.challenge2.skuydev.httpClient
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_hospital.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hospital, container, false )
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetHospital()
    }
    private fun callApiGetHospital() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<HospitalService>(httpClient)
        val call = apiRequest.getHospital()
        call.enqueue(object : Callback<List<HospitalDataItem>> {
            override fun onFailure(call: Call<List<HospitalDataItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<HospitalDataItem>>, response:
                Response<List<HospitalDataItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilHospital(response.body()!!)
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
    private fun tampilHospital(covHos: List<HospitalDataItem>) {
        listHospital.layoutManager = LinearLayoutManager(context)
        listHospital.adapter = HospitalDataAdapter(context!!, covHos) {
            val covidHospital = it
            tampilToast(context!!, covidHospital.alamat)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
