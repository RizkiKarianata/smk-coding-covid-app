package com.example.challenge2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.example.challenge2.kawalcorona.PositifService
import com.example.challenge2.kawalcorona.apiRequest
import com.example.challenge2.kawalcorona.httpClient
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false )
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetCovidPositif()
    }
    private fun callApiGetCovidPositif() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<PositifService>(httpClient)
        val call = apiRequest.getPositif()
        call.enqueue(object : Callback<List<CovidPositif>> {
            override fun onFailure(call: Call<List<CovidPositif>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<CovidPositif>>, response:
                Response<List<CovidPositif>>
            ) {
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilCovidPositif(response.body()!!)
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
    private fun tampilCovidPositif(covPos: List<CovidPositif>) {
        CovidPositifAdapter(context!!, covPos) {
            val covidPositif = it
            txtPositifCov.text = covidPositif.value
            tampilToast(context!!, covidPositif.value)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
