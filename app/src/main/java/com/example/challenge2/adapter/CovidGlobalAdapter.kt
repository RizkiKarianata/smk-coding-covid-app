package com.example.challenge2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.example.challenge2.R
import com.bumptech.glide.Glide
import com.example.challenge2.item.CovidGlobal
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_global_item.*
import java.util.*

class CovidGlobalAdapter(private val context : Context, private val items :
List<CovidGlobal>, private val listener : (CovidGlobal)-> Unit) :
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
        fun bindItem(item: CovidGlobal, listener: (CovidGlobal) -> Unit) {
            konfirmasi_c.text = item.confirmed.value.toString()
            mati_c.text = item.deaths.value.toString()
            sembuh_c.text = item.recovered.value.toString()
            val listPie = ArrayList<PieEntry>()
            val listColors = ArrayList<Int>()
            listPie.add(PieEntry(item.confirmed.value.toFloat(), "Positif"))
            listColors.add(ContextCompat.getColor(context, R.color.color_active))
            listPie.add(PieEntry(item.recovered.value.toFloat(), "Sembuh"))
            listColors.add(ContextCompat.getColor(context, R.color.color_recovered))
            listPie.add(PieEntry(item.deaths.value.toFloat(), "Meninggal"))
            listColors.add(ContextCompat.getColor(context, R.color.color_death))

            val dataSet = PieDataSet(listPie, "")
            dataSet.colors = listColors
            val pieData = PieData(dataSet)
            pieData.setValueTextSize(14F)
            pieChart.apply {
                data = pieData
                setUsePercentValues(true)
                isDrawHoleEnabled = false
                description.isEnabled = false
                setEntryLabelColor(R.color.onyx)
                setEntryLabelTextSize(8F)
                animateY(1400, Easing.EaseInOutQuad)
                setDrawEntryLabels(false)
            }
            containerView.setOnClickListener { listener(item) }
        }
    }
}