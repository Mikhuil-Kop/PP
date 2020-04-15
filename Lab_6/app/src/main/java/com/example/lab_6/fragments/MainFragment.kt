package com.example.lab_6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.lab_6.R
import com.example.lab_6.store.BuyAccept
import com.example.lab_6.store.ProductCount

//Класс, отвечающий за содержание ViewPager(товаровы в активити пользователя)
class MainFragment(product: ProductCount, adapter: ProductAdapter) : ProductFragment(product, adapter){
    var nameText: TextView? = null
    var countText: TextView? = null
    var settingsText: TextView? = null
    var buyButton : Button? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment, null)
        nameText = view.findViewById(R.id.main_fragment_name) as TextView
        countText = view.findViewById(R.id.main_fragment_count) as TextView
        settingsText = view.findViewById(R.id.main_fragment_settings) as TextView
        buyButton = view.findViewById(R.id.main_fragment_buy) as Button

        refresh()

        buyButton?.setOnClickListener {
            product.tryBuy(BuyAccept(adapter, product))
            refresh()
        }

        return view
    }

    override fun refresh() {
        nameText?.text = product.product.name
        countText?.text = product.getCount().toString()
        settingsText?.text = product.product.settings
    }

    override fun clear() {
        nameText?.text = ""
        countText?.text = ""
        settingsText?.text = ""
    }

}
