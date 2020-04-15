package com.example.lab_6

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.math.log


//Класс, отвечающий за содержание ViewPager(товаровы в активити пользователя)
class BackendFragment() : Fragment() {
    var product: ProductCount? = null
    var nameText : EditText? = null
    var countText : TextView? = null
    var settingsText : EditText? = null


    //Данная функция выполняется единожды для каждого элемента ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Достаем переменную, засунутую в Bundle в функции newInstance
    }

    //Данная функция выполняется каждый раз, когда пользователь пролистывает рядом
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Создаём view, который покажет ViewPager в mainActivity и заполняем его
        val view = inflater.inflate(R.layout.backend_fragment, null)

        //получение объектов из layout
        nameText = view.findViewById(R.id.be_fragment_name) as EditText
        countText = view.findViewById(R.id.be_fragment_count) as TextView
        settingsText = view.findViewById(R.id.be_fragment_settings) as EditText

        val buttonAdd = view.findViewById(R.id.be_fragment_add) as Button
        val buttonDel = view.findViewById(R.id.be_fragment_del) as Button

        refresh()

        //Подключение listener-ов к объектам
        nameText!!.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                product!!.product.name = nameText!!.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        settingsText!!.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                product!!.product.settings = settingsText!!.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        buttonAdd.setOnClickListener {
            product!!.addCount(1)
            countText!!.text = product!!.getCount().toString()
        }
        buttonDel.setOnClickListener {
            product!!.addCount(-1)
            countText!!.text = product!!.getCount().toString()
        }

        return view
    }

    //Обновить информацию на панели
    fun refresh(){
        nameText!!.setText(product!!.product.name)
        countText!!.text = product!!.getCount().toString()
        settingsText!!.setText(product!!.product.settings)
    }

}