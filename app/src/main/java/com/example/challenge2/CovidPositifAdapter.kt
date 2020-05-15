package com.example.challenge2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_home.*

class CovidPositifAdapter(private val context : Context, private val items :
List<CovidPositif>, private val listener : (CovidPositif)-> Unit) :
    RecyclerView.Adapter<CovidPositifAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.fragment_home,
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
        fun bindItem(item: CovidPositif, listener: (CovidPositif) -> Unit) {
            txtPositifCov.text = item.value
            containerView.setOnClickListener { listener(item) }
        }
    }
}