package com.example.lab_6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class BackendActivity : AppCompatActivity() {
    //штука, которая обеспечивает "пролистывание"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}