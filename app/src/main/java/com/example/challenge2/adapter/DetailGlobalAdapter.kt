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
import com.example.challenge2.item.DetailGlobalCovidItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.detail_global_item.*
import java.util.*

class DetailGlobalAdapter(
    private val context: Context,
    private val items: List<DetailGlobalCovidItem>,
    private val listener: (DetailGlobalCovidItem) -> Unit
) : RecyclerView.Adapter<DetailGlobalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        context, LayoutInflater.from(context).inflate(
            R.layout.detail_global_item, parent, false
        )
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        @SuppressLint("SetTextI18n")
        fun bindItem(
            item: DetailGlobalCovidItem,
            listener: (DetailGlobalCovidItem) -> Unit
        ) {
            val provinces = if (item.provinceState.isNullOrEmpty()) {
                ""
            } else {
                " (${item.provinceState})"
            }
            val date = Date(item.lastUpdate)
            txtGlobalDetailCovid.text = item.countryRegion + provinces
            txtGlobalDetailUpdate.text = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date)
            txtGlobalDetailPositif.text = item.confirmed.toString()
            txtGlobalDetailSembuh.text = item.recovered.toString()
            txtGlobalDetailMeninggal.text = item.deaths.toString()
            containerView.setOnClickListener {
                listener(item)
            }
        }
    }

}