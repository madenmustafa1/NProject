package com.mustafamaden.nproject

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var  access : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //systemUI()

        val sharedPreferences = this.getSharedPreferences("com.mustafamaden.nproject", MODE_PRIVATE)

        access = sharedPreferences.getBoolean("access", true)


        if(access){
            sharedPreferences.edit().putBoolean("access", false).apply()
            access = sharedPreferences.getBoolean("access", false)

            val constraints = Constraints.Builder ()
                .setRequiresCharging(false)
                .build ()

            val work = PeriodicWorkRequestBuilder<WorkerClass>(8, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build ()

            //val workManager = WorkManager.getInstance(this)
            //workManager.enqueue(PeriodicWorkRequest.Builder(WorkerClass::class.java, 15, TimeUnit.MINUTES, 5, TimeUnit.MINUTES).build())


            WorkManager.getInstance(applicationContext).enqueue(work)
        }
    }

    private fun systemUI(){
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
    }
}