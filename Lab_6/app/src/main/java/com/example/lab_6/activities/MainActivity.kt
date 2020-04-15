package com.example.lab_6.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.lab_6.fragments.MainFragment
import com.example.lab_6.R
import com.example.lab_6.fragments.ProductAdapter
import com.example.lab_6.store.ProductCount
import com.example.lab_6.store.STORE

//Класс, отвечающий за активити пользователя
class MainActivity : AppCompatActivity() {
    //штука, которая обеспечивает "пролистывание"
    var ourPager: ViewPager? = null
    var ourAdapter: PagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Находим на пролистыватель и подключаем адаптер
        ourPager = findViewById(R.id.main_pager)
        STORE.refreshEnabled()
        ourAdapter = ProductAdapter(supportFragmentManager, ourPager!!, STORE.enabledProducts)
        {productCount: ProductCount, adapter : ProductAdapter ->  MainFragment(productCount, adapter)}
        ourPager!!.adapter = ourAdapter
    }

}
