package com.example.lab_6.store

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.lab_6.R
import com.example.lab_6.fragments.ProductAdapter


//Класс для организации покупки. Нужен для отклика в момент завершения покупки
class BuyAccept(val adapter: ProductAdapter, val product : ProductCount) {

    fun OnSuccess(){
        adapter.refresh(false)

        val builder = NotificationCompat.Builder(adapter.pager.context)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Покупка")
            .setContentText("Покупка " + product.product.name + " успешно завершена")

        val notification: Notification = builder.build()

        val notificationManager = adapter.pager.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager!!.notify(notification_index, notification)
        notification_index++
    }

    fun onError(){
        adapter.refresh(false)

        val builder = NotificationCompat.Builder(adapter.pager.context)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Ошибка")
            .setContentText("Покупка " + product.product.name + " отменена")

        val notification: Notification = builder.build()

        val notificationManager = adapter.pager.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager!!.notify(notification_index, notification)
        notification_index++
    }
}
private var notification_index = 0