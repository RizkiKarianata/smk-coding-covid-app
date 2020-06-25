package com.example.challenge2.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.Toast
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.challenge2.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        callGlobalConfirmed()
        callGlobalRecovered()
        callGlobalDeath()
        callCovidIndonesia()
        callPieChartData()
    }
    @SuppressLint("ShowToast")
    fun callGlobalConfirmed() {
        val url = "https://api.kawalcorona.com/positif/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            txtGlobalPositif.text = jsonObject.getString(("value"))
        },
            Response.ErrorListener {
                Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
                txtGlobalPositif.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
    @SuppressLint("ShowToast")
    fun callGlobalRecovered() {
        val url = "https://api.kawalcorona.com/sembuh/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            txtGlobalSembuh.text = jsonObject.getString(("value"))
        },
            Response.ErrorListener {
                Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
                txtGlobalSembuh.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
    @SuppressLint("ShowToast")
    fun callGlobalDeath() {
        val url = "https://api.kawalcorona.com/meninggal/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            txtGlobalMeninggal.text = jsonObject.getString(("value"))
        },
            Response.ErrorListener {
                Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
                txtGlobalMeninggal.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
    @SuppressLint("ShowToast")
    fun callCovidIndonesia() {
        val url = "https://covid19.mathdro.id/api/countries/Indonesia"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it)
            val last = JSONObject(it.toString())
            val positif = jsonObject.getJSONObject("confirmed")
            val sembuh = jsonObject.getJSONObject("recovered")
            val meninggal = jsonObject.getJSONObject("deaths")
            txtLokalPositif.text = positif.getString(("value"))
            txtLokalSembuh.text = sembuh.getString(("value"))
            txtLokalMeninggal.text = meninggal.getString(("value"))
            txtPerbarui.text = last.getString(("lastUpdate"))
        },
            Response.ErrorListener {
                Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
                txtLokalPositif.text = "-"
                txtLokalSembuh.text = "-"
                txtLokalMeninggal.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
    @SuppressLint("ShowToast")
    fun callPieChartData() {
        val url = "https://covid19.mathdro.id/api/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it)
            val positif = jsonObject.getJSONObject("confirmed")
            val sembuh = jsonObject.getJSONObject("recovered")
            val meninggal = jsonObject.getJSONObject("deaths")
            val valuePositif = positif.getString(("value"))
            val valueSembuh = sembuh.getString(("value"))
            val valueMeninggal = meninggal.getString(("value"))

            val listPie = ArrayList<PieEntry>()
            val listColors = ArrayList<Int>()
            listPie.add(PieEntry(valuePositif.toFloat(), "Positif"))
            context?.let { it1 -> ContextCompat.getColor(it1, R.color.color_confirmed) }?.let { it2 ->
                listColors.add(
                    it2
                )
            }
            listPie.add(PieEntry(valueSembuh.toFloat(), "Sembuh"))
            context?.let { it1 -> ContextCompat.getColor(it1, R.color.color_recovered) }?.let { it2 ->
                listColors.add(
                    it2
                )
            }
            listPie.add(PieEntry(valueMeninggal.toFloat(), "Meninggal"))
            context?.let { it1 -> ContextCompat.getColor(it1, R.color.color_death) }?.let { it2 ->
                listColors.add(
                    it2
                )
            }
            val dataSet = PieDataSet(listPie, "")
            dataSet.colors = listColors
            val pieData = PieData(dataSet)
            pieData.setValueTextSize(14F)
            worldSummaryPie.apply {
                data = pieData
                setUsePercentValues(true)
                isDrawHoleEnabled = false
                description.isEnabled = false
                setEntryLabelColor(R.color.onyx)
                setEntryLabelTextSize(8F)
                animateY(1400, Easing.EaseInOutQuad)
                setDrawEntryLabels(false)
            }
        },
            Response.ErrorListener {
                Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
}
