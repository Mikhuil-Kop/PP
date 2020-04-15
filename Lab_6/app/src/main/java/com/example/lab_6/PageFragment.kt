package com.example.lab_6

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*

//Класс, отвечающий за содержание ViewPager(товаровы в активити пользователя)
class PageFragment() : Fragment() {
    var pageNumber: Int = 0
    var product: ProductCount? = null
    var nameText: TextView? = null
    var countText: TextView? = null
    var settingsText: TextView? = null

    //Данная функция выполняется единожды для каждого элемента ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Достаем переменную, засунутую в Bundle в функции newInstance
        pageNumber = arguments!!.getInt("arg_page_number")
        val rnd = Random()
    }

    //Данная функция выполняется каждый раз, когда пользователь проли
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Создаём view, который покажет ViewPager в mainActivity и заполняем его
        val view: View = inflater.inflate(R.layout.fragment, null)
        product = STORE.getProduct(pageNumber)

        nameText = view.findViewById(R.id.main_fragment_name) as TextView
        countText = view.findViewById(R.id.main_fragment_count) as TextView
        settingsText = view.findViewById(R.id.main_fragment_settings) as TextView

        refresh()

        return view
    }

    fun refresh() {
        product = STORE.getProduct(pageNumber)
        nameText!!.text = product!!.product.name
        countText!!.text = product!!.getCount().toString()
        settingsText!!.text = product!!.product.settings
    }

}
