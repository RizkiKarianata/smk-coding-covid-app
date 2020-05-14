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
            0 -> { return HomeFragment() }
            1 -> { return CountryFragment() }
            2 -> { return ProvincesFragment() }
            3 -> { return ProfileFragment() }
            else -> {
                return HomeFragment()
            }
        }
    }
    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}