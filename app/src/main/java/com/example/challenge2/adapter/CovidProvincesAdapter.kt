package com.example.challenge2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge2.R
import com.example.challenge2.item.CovidProvincesItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_provinces_item.*
import java.util.*

class CovidProvincesAdapter(private val context : Context, private val items :
List<CovidProvincesItem>, private val listener : (CovidProvincesItem)-> Unit) :
    RecyclerView.Adapter<CovidProvincesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.covid_provinces_item,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context : Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{
        @SuppressLint("SetTextI18n")
        fun bindItem(item: CovidProvincesItem, listener: (CovidProvincesItem) -> Unit) {
            txtKodeProvinsi.text = "Kode Provinsi " + item.attributes.kodeProvi
            txtProvincesMeninggal.text = item.attributes.kasusMeni.toString()
            txtProvincesSembuh.text = item.attributes.kasusSemb.toString()
            txtProvincesPositif.text = item.attributes.kasusPosi.toString()
            txtProvincesCovid.text = item.attributes.provinsi
            containerView.setOnClickListener { listener(item) }
        }
    }
}