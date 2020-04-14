package com.example.lab_6

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*


//Класс, отвечающий за содержание ViewPager(товаровы в активити пользователя)
class BackendFragment() : Fragment() {
    var pageNumber = 0
    var backColor = 0

    //Данная функция выполняется единожды для каждого элемента ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Достаем переменную, засунутую в Bundle в функции newInstance
        pageNumber = arguments!!.getInt("arg_page_number")
        val rnd = Random()
        backColor = Color.argb(40, rnd.nextInt(10), rnd.nextInt(10), rnd.nextInt(10))
    }

    //Данная функция выполняется каждый раз, когда пользователь проли
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Создаём view, который покажет ViewPager в mainActivity и заполняем его
        val view = inflater.inflate(R.layout.backend_fragment, null)
        val tvPage = view.findViewById(R.id.be_fragment_name) as EditText

        tvPage.setText("Page$pageNumber")
        tvPage.setBackgroundColor(backColor)
        return view
    }

}

//СТАТИКИ НЕТ, БЛИН
fun backendNewInstance(page: Int): BackendFragment {
    //Создаем экземпляр PageFragment и запихиваем внутрь него Bundle с хранимым интом
    val pageFragment = BackendFragment()
    val arguments = Bundle()
    arguments.putInt("arg_page_number", page)
    pageFragment.arguments = arguments
    return pageFragment
}