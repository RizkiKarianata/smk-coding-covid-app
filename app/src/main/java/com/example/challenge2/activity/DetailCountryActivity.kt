package com.example.challenge2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge2.R
import android.annotation.SuppressLint
import com.example.challenge2.session.SessionCountry
import android.util.DisplayMetrics
import android.widget.ArrayAdapter
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
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
import kotlinx.android.synthetic.main.activity_detail_country.*
import org.json.JSONObject
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlin.collections.ArrayList

class DetailCountryActivity : AppCompatActivity() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail_country)
    initView()
}
    private fun initView() {
        callDetailCountry()
    }
    @SuppressLint("ShowToast")
    private fun callDetailCountry() {
        val url = "https://api.kawalcorona.com/sembuh/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            txtNamaNegara.text = jsonObject.getString(("value"))
        },
            Response.ErrorListener {
                Toast.makeText(this, "Kesalahan", Toast.LENGTH_SHORT)
                txtNamaNegara.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
