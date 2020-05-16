package com.example.challenge2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.hospital_item.*

class HospitalDataAdapter(private val context : Context, private val items :
List<HospitalDataItem>, private val listener : (HospitalDataItem)-> Unit) :
    RecyclerView.Adapter<HospitalDataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.hospital_item,
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
        fun bindItem(item: HospitalDataItem, listener: (HospitalDataItem) -> Unit) {
            txtHospital.text = item.nama
            txtProvinsi.text = item.provinsi
            txtTelepon.text = item.telepon
            txtKeterangan.text = item.keterangan
            Glide.with(context).load(R.drawable.ic_bacteria2).into(imgUser)
            containerView.setOnClickListener { listener(item) }
        }
    }
}