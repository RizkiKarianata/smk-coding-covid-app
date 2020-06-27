package com.example.challenge2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.challenge2.R
import com.example.challenge2.item.GlobalCovidItem
import com.example.challenge2.session.SessionCountry
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_global_item.*
import java.util.*

class CovidGlobalAdapter(private val context : Context, private val items :
List<GlobalCovidItem>, private val listener : (GlobalCovidItem)-> Unit) :
    RecyclerView.Adapter<CovidGlobalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.covid_global_item,
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
        fun bindItem(item: GlobalCovidItem, listener: (GlobalCovidItem) -> Unit) {
            val provinces = if (item.provinceState.isNullOrEmpty()) {
                ""
            } else {
                " (${item.provinceState})"
            }
            val date = Date(item.lastUpdate)
            txtGlobal.text = item.countryRegion + provinces
            txtInfo.text = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date)
            containerView.setOnClickListener { listener(item) }
        }
    }
}