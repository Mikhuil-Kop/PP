package com.example.laba1kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rpp_lab_1.CustomAdapter

class SecondActivity : AppCompatActivity() {

    lateinit var recycleView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        recycleView = findViewById(R.id.recyclerView)

        var layoutManager = LinearLayoutManager(this)
        recycleView.layoutManager = layoutManager;
        recycleView.setHasFixedSize(true)


        var myList = ArrayList<Int>()
        for (i in 1..999999)
            myList.add(i)

        recycleView.adapter = CustomAdapter(myList, this)
    }
}
