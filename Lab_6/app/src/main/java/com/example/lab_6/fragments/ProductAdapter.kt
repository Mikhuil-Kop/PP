package com.example.lab_6.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.lab_6.store.ProductCount
import com.example.lab_6.store.STORE

//Адаптер, на основе которого написаны адаптеры мейна и бекенда
class ProductAdapter<T : ProductFragment>(fm: FragmentManager?, private val pager:ViewPager, private val products: MutableList<ProductCount>, private val construct : (ProductCount)-> T) : FragmentPagerAdapter(fm!!, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var all = mutableListOf<T>();
    init{
        pager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                //Обновление необновленной записи при попадании на неё
                if(all[position].mustBeRefreshed){
                    all[position].refresh()
                    all[position].mustBeRefreshed = false
                }
            }

            //Абстрактные функции
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        refresh()
    }

    override fun getItem(position: Int): Fragment {
        return all[position]
    }

    override fun getCount(): Int {
        return all.size;
    }

    //Функция востанавливает правильный размер и обнавляет весь ViewPager
    fun refresh(){
        //Смена размера
        if(all.size < products.size){
            for(i in all.size until products.size)
                all.add(construct(products[i]))
            notifyDataSetChanged()
            pager.currentItem = 0;
        }
        if(all.size > products.size){
            while (all.size != products.size)
                all.removeAt(all.size - 1)
            notifyDataSetChanged()
            pager.currentItem = 0;
        }


        //Перерисовка
        for (i in 0 until all.size) {
            all[i].product = products[i]
            all[i].mustBeRefreshed = true
        }

        if(all.size != 0) {
            all[pager.currentItem].refresh()
            all[pager.currentItem].mustBeRefreshed = false;
        }

    }

    private fun add(index: Int){
        var newPos = 0

        if (STORE.getFullSize() == 0)
            STORE.addProduct(0)
        else {
            newPos = index + 1
            STORE.addProduct(newPos)
        }
        refresh()
        pager.currentItem = newPos
    }

    private fun remove(index: Int){
        STORE.removeProduct(pager.currentItem)
        refresh()
        pager.currentItem = pager.currentItem - 1
    }
}