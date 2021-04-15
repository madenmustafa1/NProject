package com.mustafamaden.nproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.*
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class WorkerClass(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    lateinit var randomText : String
    private val arrayList = ArrayList<String>()

    override fun doWork(): Result {
        randomText()
        createNotification(title, randomText)

        return Result.success()
    }

    private fun createNotification(title: String, description: String){

        var notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                    NotificationChannel("101", "NProject", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val bitmapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.nphoto)

        val notificationBuilder = NotificationCompat.Builder(applicationContext, "101")
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.notification_message)
                .setColor(Color.GREEN)
                .setLargeIcon(bitmapLargeIcon)

        notificationManager.notify(1, notificationBuilder.build())
    }

    var title : String = "Tık tık... Bildirim geldi!"

    fun randomText(){
        arrayList.add("Notification")
	arrayList.add("Project")
	arrayList.add("is working!")
        

        val randomInt = Random.nextInt(0,arrayList.size)
        randomText = arrayList[randomInt]
    }


}
