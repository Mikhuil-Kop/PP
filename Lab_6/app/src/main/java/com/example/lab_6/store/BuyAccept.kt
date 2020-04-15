package com.example.lab_6.store

import com.example.lab_6.fragments.ProductAdapter


//Класс для организации покупки. Нужен для отклика в момент завершения покупки
class BuyAccept(val adapter: ProductAdapter) {

    fun OnSuccess(){
        //AlertWindow("ADADAS")
        adapter.refresh(false)
    }

    fun onError(){
        //AlertWindow("NONONO")
        adapter.refresh(false)
    }
}