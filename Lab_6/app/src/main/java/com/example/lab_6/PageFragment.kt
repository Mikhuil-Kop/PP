package com.example.lab_6

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lab_6.R
import java.util.*

//Класс, отвечающий за содержание ViewPager(товаровы в активити пользователя)
class PageFragment() : Fragment() {
    var pageNumber = 0
    var backColor = 0

    //Данная функция выполняется единожды для каждого элемента ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Достаем переменную, засунутую в Bundle в функции newInstance
        pageNumber = arguments!!.getInt("arg_page_number")
        val rnd = Random()
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    //Данная функция выполняется каждый раз, когда пользователь проли
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Создаём view, который покажет ViewPager в mainActivity и заполняем его
        val view: View = inflater.inflate(R.layout.fragment, null)
        val tvPage = view.findViewById(R.id.textView) as TextView

        tvPage.text = "Page$pageNumber"
        tvPage.setBackgroundColor(backColor)
        return view
    }

}

//СТАТИКИ НЕТ, БЛИН
fun newInstance(page: Int): PageFragment {
    //Создаем экземпляр PageFragment и запихиваем внутрь него Bundle с хранимым интом
    val pageFragment = PageFragment()
    val arguments = Bundle()
    arguments.putInt("arg_page_number", page)
    pageFragment.arguments = arguments
    return pageFragment
}
