package com.example.lab_6.fragments


import androidx.fragment.app.Fragment
import com.example.lab_6.store.ProductCount

abstract class ProductFragment(var product: ProductCount, var adapter : ProductAdapter) : Fragment() {
    var mustBeRefreshed = true

    //Абстрактные
    abstract fun refresh()
    abstract fun clear()
}