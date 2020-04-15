package com.example.lab_6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab_6.R
import com.example.lab_6.store.Product
import com.example.lab_6.store.ProductCount

abstract class ProductFragment(var product: ProductCount) : Fragment() {
    var mustBeRefreshed = true

    //Данная функция выполняется каждый раз, когда пользователь пролистывает рядом
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Создаём view, который покажет ViewPager в mainActivity и заполняем его
        val view = inflater.inflate(R.layout.backend_fragment, null)

        initializeViews(view)
        refresh()
        initializeListeners()

        return view
    }

    //Абстрактные
    abstract fun refresh()
    abstract fun initializeViews(view: View)
    abstract fun initializeListeners()
}