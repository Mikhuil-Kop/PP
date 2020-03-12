package com.example.rpp_lab2_last

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.size
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.fragment_fragment_one.*

class ViewPager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        val previous = intent.extras!!.getString("previousImage")
        val current = intent.extras!!.getString("currentImage")
        val next = intent.extras!!.getString("nextImage")


        viewPagerAdapter.addFragment(FragmentOne(previous!!), "one")
        viewPagerAdapter.addFragment(FragmentOne(current!!), "two")
        viewPagerAdapter.addFragment(FragmentOne(next!!), "three")

        viewPager.adapter = viewPagerAdapter
        viewPager.setCurrentItem(1)
1
    "{P:LRDCZ"}
}
