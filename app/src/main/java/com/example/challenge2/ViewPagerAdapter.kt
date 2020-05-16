package com.example.challenge2

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val JUMLAH_MENU = 4
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> { return CountryFragment() }
            1 -> { return ProvincesFragment() }
            2 -> { return HospitalFragment() }
            3 -> { return ProfileFragment() }
            else -> {
                return HospitalFragment()
            }
        }
    }
    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}