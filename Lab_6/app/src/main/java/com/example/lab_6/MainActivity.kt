package com.example.lab_6

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

//Класс, отвечающий за активити пользователя
class MainActivity : AppCompatActivity() {
    //штука, которая обеспечивает "пролистывание"
    var ourPager: ViewPager? = null
    var ourAdapter: PagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Находим на пролистыватель и подключаем адаптер
        ourPager = findViewById(R.id.main_pager)
        ourAdapter = MyFragmentPagerAdapter(supportFragmentManager)
        ourPager!!.adapter = ourAdapter
        //Засовываем в "пролистыватель" пустой listener. Можно удалить данный фрагмент
        ourPager!!.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                override fun onPageSelected(position: Int) {
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
    }

    //Основные отличия. Кнопки
    //Добавление нового элемента в массив
    fun addToStoreButton(view: View?): Unit {
        STORE.addProduct(ourPager!!.currentItem)
        ourAdapter!!.notifyDataSetChanged()
    }

    //Удаление текущего элемента
    fun removeFromStoreButton(view: View?): Unit {
        STORE.removeProduct(ourPager!!.currentItem)
        ourAdapter!!.notifyDataSetChanged()
        ourPager!!.currentItem = ourPager!!.currentItem - 1
    }

    //Адаптер, который создает экземпляр PageFragment, когда это необходимо
    private class MyFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        var all = mutableListOf<BackendFragment>();

        override fun getItem(position: Int): Fragment {
            val pageFragment = PageFragment()
            val arguments = Bundle()
            arguments.putInt("arg_page_number", position)
            pageFragment.arguments = arguments
            return pageFragment
        }

        override fun getCount(): Int {
            return STORE.getFullSize();
        }

        //Функция перезагрузки всех элементов
        fun refresh(){
            for (i in 0..all.size)
                all[i].refresh()
        }

    }
}
