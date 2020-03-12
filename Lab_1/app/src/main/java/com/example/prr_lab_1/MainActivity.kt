package com.example.laba1kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(MyRunnable(this), 2000)
    }

    private class MyRunnable(mainActivity : MainActivity) : Runnable{
        val mainActivity = mainActivity

        override fun run() {
            Thread.sleep(0)
            val intent = Intent(mainActivity, SecondActivity::class.java)
            mainActivity.startActivity(intent)
            mainActivity.finish()
        }
    }
}


