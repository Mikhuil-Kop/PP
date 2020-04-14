package com.example.lab_6

import android.os.Bundle
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
            object : OnPageChangeListener {
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

    //Адаптер, который создает экземпляр PageFragment, когда это необходимо
    private class MyFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return mainNewInstance(position)
        }

        override fun getCount(): Int {
            return 50;
        }
    }
}
