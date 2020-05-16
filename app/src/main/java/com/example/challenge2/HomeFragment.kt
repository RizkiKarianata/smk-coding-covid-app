package com.example.challenge2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.Toast
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        callGlobalConfirmed()
        callGlobalRecovered()
        callGlobalDeath()
    }
    @SuppressLint("ShowToast")
    fun callGlobalConfirmed() {
        val url = "https://api.kawalcorona.com/positif/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            txtConfirmedCov.text = jsonObject.getString(("value"))
        },
        Response.ErrorListener {
            Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
            txtConfirmedCov.text = "-"
        })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
    @SuppressLint("ShowToast")
    fun callGlobalRecovered() {
        val url = "https://api.kawalcorona.com/sembuh/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            txtRecoveredCov.text = jsonObject.getString(("value"))
        },
            Response.ErrorListener {
                Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
                txtRecoveredCov.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
    @SuppressLint("ShowToast")
    fun callGlobalDeath() {
        val url = "https://api.kawalcorona.com/meninggal/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            txtDeathCov.text = jsonObject.getString(("value"))
        },
            Response.ErrorListener {
                Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
                txtDeathCov.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
}
