package com.example.lab_6

//Класс-список товаров в магазине
class Store {
    //Основной массив
    private var products = mutableListOf<ProductCount>();
    //Массив доступных товаров на основе основного массива
    private var enabledProducts = mutableListOf<ProductCount>();

    //Размеры
    fun getFullSize():Int{
        return products.size;
    }

    fun getEnabledSize():Int{
        return enabledProducts.size;
    }

    fun addProduct(index : Int) : ProductCount{
        val productCount = ProductCount(Product(), 0)
        products.add(index, productCount)
        return productCount
    }

    fun removeProduct(index : Int){
         products.removeAt(index)
    }

    fun getProduct(index: Int):ProductCount{
        return products[index]
    }
}

//Так как в Kotlin нет статики, приходится использовать эту переменную как костыль
val STORE = Store();

//Класс для определения количества продуктов в магазине или в корзине
class ProductCount(val product: Product, count: Int){
    private var count = count

    fun getCount() : Int{
       return count;
    }

    fun addCount(change : Int){
        count += change
        if(count < 0)
            count = 0
    }
}