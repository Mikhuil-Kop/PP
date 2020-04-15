package com.example.lab_6.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_6.R


class StartActivity : AppCompatActivity() {
    //штука, которая обеспечивает "пролистывание"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    //Нажатия на кнопки
    fun toMainButton(view: View?): Unit { // выводим сообщение
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

    fun toBackendButton(view: View?) { // выводим сообщение
        val intent = Intent(this, BackendActivity::class.java)
        this.startActivity(intent)
    }
}