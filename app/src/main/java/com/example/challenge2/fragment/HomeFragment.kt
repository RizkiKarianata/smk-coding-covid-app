package com.example.challenge2.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.Toast
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.challenge2.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

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
    }
    @SuppressLint("ShowToast")
    fun callGlobalConfirmed() {
        val url = "https://api.kawalcorona.com/positif/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            konfirmasi_c.text = jsonObject.getString(("value"))
        },
            Response.ErrorListener {
                Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
                konfirmasi_c.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
    @SuppressLint("ShowToast")
    fun callGlobalRecovered() {
        val url = "https://api.kawalcorona.com/sembuh/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            sembuh_c.text = jsonObject.getString(("value"))
        },
            Response.ErrorListener {
                Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
                sembuh_c.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
    @SuppressLint("ShowToast")
    fun callGlobalDeath() {
        val url = "https://api.kawalcorona.com/meninggal/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            mati_c.text = jsonObject.getString(("value"))
        },
            Response.ErrorListener {
                Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT)
                mati_c.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
}
