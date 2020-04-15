package com.example.lab_6

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

//Класс, отвечающий за активити бэкенда. Почти точная копия MainActivity за исключением нескольких кнопок
class BackendActivity : AppCompatActivity() {
    //штука, которая обеспечивает "пролистывание"
    var ourPager: ViewPager? = null
    var ourAdapter: MyFragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("D", "Бэкенд запущен");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backend)
        //Находим на пролистыватель и подключаем адаптер
        ourPager = findViewById(R.id.backend_pager)
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
        var newPos = 0

        if(STORE.getFullSize() == 0)
            STORE.addProduct(0)
        else {
            newPos = ourPager!!.currentItem + 1
            STORE.addProduct(newPos)
        }
        ourAdapter!!.refresh()
        ourPager!!.currentItem = newPos
    }

    //Удаление текущего элемента
    fun removeFromStoreButton(view: View?): Unit {
        STORE.removeProduct(ourPager!!.currentItem)
        ourAdapter!!.notifyDataSetChanged()
        ourPager!!.currentItem = ourPager!!.currentItem - 1

        ourAdapter!!.refresh()
    }

    //Адаптер, который создает экземпляр PageFragment, когда это необходимо
    class MyFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        var all = mutableListOf<BackendFragment>();

        override fun getItem(position: Int): Fragment {
            //Создаем экземпляр PageFragment и запихиваем внутрь него Bundle с хранимым интом
            val pageFragment = BackendFragment()
            val arguments = Bundle()

            all.add(pageFragment)
            arguments.putInt("arg_page_number", position)
            pageFragment.arguments = arguments

            return pageFragment
        }

        override fun getCount(): Int {
            val size = STORE.getFullSize();
            return size
        }


        fun refresh(){
            notifyDataSetChanged()

            for (i in 0 until all.size)
                all[i].refresh()
        }
    }
}