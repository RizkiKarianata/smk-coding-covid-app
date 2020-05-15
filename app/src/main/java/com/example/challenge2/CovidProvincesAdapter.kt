package com.example.challenge2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_provinces_item.*

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
            txtProvinces.text = item.attributes.provinsi
            txtPositif.text = "Confirmed : " + item.attributes.kasusPosi.toString()
            txtMeninggal.text = "Deaths : " + item.attributes.kasusMeni.toString()
            txtSembuh.text = "Recovered : " + item.attributes.kasusSemb.toString()
            Glide.with(context).load(R.drawable.ic_bacteria2).into(imgUser)
            containerView.setOnClickListener { listener(item) }
        }
    }
}