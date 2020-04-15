package com.example.lab_6.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.lab_6.store.ProductCount

//Адаптер, на основе которого написаны адаптеры мейна и бекенда
class ProductAdapter(fm: FragmentManager?, val pager:ViewPager, private val products: MutableList<ProductCount>, private val construct : (ProductCount, ProductAdapter)-> ProductFragment) : FragmentPagerAdapter(fm!!, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var all = mutableListOf<ProductFragment>();
    init{
        pager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                //Обновление необновленной записи при попадании на неё
                if(position >= 0 && position < all.size && all[position].mustBeRefreshed){
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
                if(position >= 0 && position < all.size && all[position].mustBeRefreshed){
                    all[position].refresh()
                    all[position].mustBeRefreshed = false
                }
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
    fun refresh(mainThread : Boolean = true){
        //Смена размера
        if(all.size < products.size){
            for(i in all.size until products.size)
                all.add(construct(products[i], this))
            notifyDataSetChanged()
            //pager.currentItem = 0;
        }
        if(all.size > products.size){
            while (all.size != products.size) {
                if(mainThread)
                all[all.size - 1].clear()
                all.removeAt(all.size - 1)
            }
            notifyDataSetChanged()
            //pager.currentItem = 0;
        }


        //Перерисовка
        for (i in 0 until all.size) {
            all[i].product = products[i]
            all[i].mustBeRefreshed = true
        }

        if(mainThread && all.size != 0) {
            if(pager.currentItem >= all.size)
                pager.currentItem--
            all[pager.currentItem].refresh()
            all[pager.currentItem].mustBeRefreshed = false;
        }

    }

}