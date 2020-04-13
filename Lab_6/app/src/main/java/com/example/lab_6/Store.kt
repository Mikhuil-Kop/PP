package com.example.lab_6

//Класс-список товаров в магазине
class Store {
    var product = arrayOf<Product>();
}

//Так как в Kotlin нет статики, приходится использовать эту переменную как костыль
val STORE = Store();

//Класс для определения количества продуктов в магазине или в корзине
data class ProductCount(val product: Product, var count: Int){}