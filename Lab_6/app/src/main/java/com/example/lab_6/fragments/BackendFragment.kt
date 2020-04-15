package com.example.lab_6.fragments

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
import com.example.lab_6.R
import com.example.lab_6.store.ProductCount


//Класс, отвечающий за содержание ViewPager(товаровы в активити пользователя)
class BackendFragment(product: ProductCount) : ProductFragment(product) {
    private var fragmentView : View? = null

    private var nameText : EditText? = null
    private var countText : TextView? = null
    private var settingsText : EditText? = null
    private var buttonAdd : Button? = null
    private var buttonDel : Button? = null

    override fun initializeViews(view: View) {
        fragmentView = view
        nameText = view.findViewById(R.id.be_fragment_name) as EditText
        countText = view.findViewById(R.id.be_fragment_count) as TextView
        settingsText = view.findViewById(R.id.be_fragment_settings) as EditText

        buttonAdd = view.findViewById(R.id.be_fragment_add) as Button
        buttonDel = view.findViewById(R.id.be_fragment_del) as Button
    }

    override fun initializeListeners() {
        //Подключение listener-ов к объектам
        nameText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(refreshing && active) {
                    Log.d("L", product.product.name+ " -> " + nameText!!.text.toString() + "/" + product.product)
                    product.product.name = nameText!!.text.toString()

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        settingsText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(refreshing && active)
                    product.product.settings = settingsText!!.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        buttonAdd!!.setOnClickListener {
            product.addCount(1)
            countText!!.text = product.getCount().toString()
        }
        buttonDel!!.setOnClickListener {
            product.addCount(-1)
            countText!!.text = product.getCount().toString()
        }

    }

    override fun refresh() {
        //Хрень нужна для блокирования TextChangeListener-а
        refreshing = false
        nameText?.setText(product.product.name)
        countText?.text = product.getCount().toString()
        settingsText?.setText(product.product.name)
        refreshing = true
    }


}