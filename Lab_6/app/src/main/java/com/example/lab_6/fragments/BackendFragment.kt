package com.example.lab_6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.lab_6.R
import com.example.lab_6.store.ProductCount


//Класс, отвечающий за содержание ViewPager(товаровы в активити пользователя)
class BackendFragment(product: ProductCount, adapter: ProductAdapter) : ProductFragment(product,adapter) {
    private var nameText : EditText? = null
    private var countText : TextView? = null
    private var settingsText : EditText? = null

    private var buttonAdd : Button? = null
    private var buttonDel : Button? = null
    private var buttonSave : Button? = null

    //Данная функция выполняется каждый раз, когда пользователь пролистывает рядом
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.backend_fragment ,null)
        nameText = view.findViewById(R.id.be_fragment_name) as EditText
        countText = view.findViewById(R.id.be_fragment_count) as TextView
        settingsText = view.findViewById(R.id.be_fragment_settings) as EditText

        buttonAdd = view.findViewById(R.id.be_fragment_add) as Button
        buttonDel = view.findViewById(R.id.be_fragment_del) as Button
        buttonSave = view.findViewById(R.id.be_fragment_save) as Button

        refresh()

        //Подключение listener-ов к объектам
        buttonAdd!!.setOnClickListener {
            product.addCount(1)
            countText!!.text = product.getCount().toString()
        }
        buttonDel!!.setOnClickListener {
            product.addCount(-1)
            countText!!.text = product.getCount().toString()
        }
        buttonSave!!.setOnClickListener {
            product.product.name = nameText?.text.toString();
            product.product.settings = settingsText?.text.toString();
        }

        return view
    }

    override fun refresh() {
        nameText?.setText(product.product.name)
        countText?.text = product.getCount().toString()
        settingsText?.setText(product.product.settings)
    }

    override fun clear() {
        nameText?.setText("")
        countText?.text = ""
        settingsText?.setText("")
    }


}