package com.example.infedistest.ui.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.infedistest.R
import com.example.infedistest.ui.main.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class HostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_tab_host, container, false)
    }

    private lateinit var viewPager: ViewPager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager = view.findViewById(R.id.pager)
        val tab = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPagerAdapter = activity?.supportFragmentManager?.let {
            ViewPagerAdapter(
                it
            )
        }
        viewPager.adapter = viewPagerAdapter
        tab.setupWithViewPager(viewPager)
    }
}

