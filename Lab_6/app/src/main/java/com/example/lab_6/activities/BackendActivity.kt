package com.example.lab_6.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.lab_6.fragments.BackendFragment
import com.example.lab_6.R
import com.example.lab_6.fragments.*
import com.example.lab_6.store.*

//Класс, отвечающий за активити бэкенда. Почти точная копия MainActivity за исключением нескольких кнопок
class BackendActivity : AppCompatActivity() {
    //штука, которая обеспечивает "пролистывание"
    var ourPager: ViewPager? = null
    var ourAdapter: ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("D", "Бэкенд запущен");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backend)
        //Находим на пролистыватель и подключаем адаптер
        ourPager = findViewById(R.id.backend_pager)
        ourAdapter = ProductAdapter(supportFragmentManager, ourPager!!, STORE.products)
        {productCount: ProductCount, adapter: ProductAdapter ->  BackendFragment(productCount, adapter)}
        ourPager!!.adapter = ourAdapter
    }

    //Основные отличия. Кнопки
    //Добавление нового элемента в массив
    fun addToStoreButton(view: View?): Unit {
        ourPager!!.isActivated = true
        STORE.addProduct(ourPager!!.currentItem)
        ourAdapter!!.refresh()
    }

    //Удаление текущего элемента
    fun removeFromStoreButton(view: View?): Unit {
        if (STORE.products.size != 0) {
            STORE.removeProduct(ourPager!!.currentItem)
            ourAdapter!!.refresh()
        } else
            ourPager!!.isActivated = false
    }

}