package com.example.rpp_lab_1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.laba1kotlin.R
import kotlinx.android.synthetic.main.item_layout.view.*


class CustomAdapter(val items : ArrayList<Int>, val context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.number?.setBackgroundColor(if(items[position] % 2 == 1) Color.WHITE else Color.LTGRAY)
        holder.number?.text = intToString(items[position])
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val number = view.item_textView
        var image = view.item_icon
    }

    private fun intToString(number: Int) : String
    {
        val arr1 = arrayOf<Array<String>>(
            arrayOf<String>("", "Один", "Два", "Три", "Четыре", "Пять", "Шесть", "Семь", "Восемь", "Девять", "Десять",
                "Одиннадцать", "Двенадцать", "Тринадцать", "Четырнадцать", "Пятнадцать", "Шеснадцать", "Сменадцать", "Восемнадцать", "Девятнадцать"),

            arrayOf<String>("", "Одна Тысяча", "Две Тысячи", "Три Тысячи", "Четыре Тысячи", "Пять Тысяч", "Шесть Тысяч", "Семь Тысяч", "Восемь Тысяч", "Девять Тысяч", "Десять Тысяч",
                "Одиннадцать Тысяч", "Двенадцать Тысяч", "Тринадцать Тысяч", "Четырнадцать Тысяч", "Пятнадцать Тысяч", "Шеснадцать Тысяч", "Сменадцать Тысяч", "Восемнадцать Тысяч", "Девятнадцать Тысяч")
        )
        val arr2 = arrayOf<String>("", "", "Двадцать", "Тридцать", "Сорок", "Пятьдесят", "Шестьдесят", "Семьдесят", "Восемьдесят", "Девяносто")
        val arr3 = arrayOf<String>("","Сто","Двести","Триста","Четыреста","Пятьсот","Шестьсот", "Семьсот", "Восемьсот","Девятьсот")


        var str = ""
        var ind :Int = 0
        var num :Int = number

        while(num != 0){
            var i1 : Int = num % 10
            num = (num / 10)
            var i2 : Int = num % 10
            num = (num / 10)
            var i3 : Int = num % 10
            num = (num / 10)

            if(i2 == 1)
                i1 += 10

            str = arr3[i3] + " " + arr2[i2] + " " + arr1[ind][i1] + " " + str
            ind++
        }

        return str
    }

}
