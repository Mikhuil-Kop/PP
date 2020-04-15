package com.example.lab_6.store

import java.io.FileInputStream
import java.io.FileOutputStream
import kotlin.math.abs
import kotlin.math.max
import kotlin.random.Random

//Класс-список товаров в магазине
class Store {
    //Основной массив
    var products = mutableListOf<ProductCount>();
    //Массив доступных товаров на основе основного массива
    var enabledProducts = mutableListOf<ProductCount>();

    //Размеры

    fun addProduct(index : Int) : ProductCount {
        val productCount = ProductCount(Product(), 0)
        products.add(index, productCount)
        return productCount
    }

    fun removeProduct(index : Int){
         products.removeAt(index)
    }

    //Говно, я знаю. Но лень делать иначе
    fun refreshEnabled(){
        enabledProducts.clear()
        for(i in 0 until products.size)
            if(products[i].getUnsoldCount() > 0)
                enabledProducts.add(products[i])
    }

    //Сохранение
//    fun toFile(fos : FileOutputStream){
//
//    }

    //Загрузка
//    fun fromFile(fis: FileInputStream){
//        val len = fis.read()
//        for(i in 0 until len){
//            val prod = addProduct(i)
//            bytes = byte[fin.available()];
//                        fin.read(bytes);
//                        String text = new String (bytes);
//        }
//
//    }

}

//Так как в Kotlin нет статики, приходится использовать эту переменную как костыль
val STORE = Store();

//Класс для определения количества продуктов в магазине или в корзине
class ProductCount(val product: Product, count: Int){
    //Количество товаров "на складе"
    private var count = count
    //Количество товаров, которые будут куплены
    private var willBuy = 0

    //Получить количество объектов, с учётом запросов на покупку
    fun getCount() : Int{
       return max(count - willBuy,0);
    }

    //Поучить колличество непроданных объектов
    fun getUnsoldCount() : Int{
        return count
    }

    fun addCount(change : Int){
        count += change
        if(count < 0)
            count = 0
    }

    //Купить
    fun tryBuy(buyAccept: BuyAccept){
        willBuy++
        Thread(Runnable { buy(buyAccept) }).start()
    }


    //ФУНКЦИЯ, КОТОРАЯ ВЫПОЛНЯЕТСЯ 3-5 СЕКУНД
    private fun buy(buyAccept: BuyAccept) {
        //Задержка
        val random = Random(System.currentTimeMillis())
        Thread.sleep(abs(random.nextLong() % 3000) + 2000 )

        //Осуществить покупку
        willBuy--
        if (count > 0){
            count--
            buyAccept.OnSuccess()
        }else {//Товаров не хватило
            buyAccept.onError()
        }

        //Обновить доступные
        if(count <= 0)
            STORE.refreshEnabled()
    }


}