package com.example.oroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.IO){
            val time = measureTimeMillis {
//                val firstAnswer = networkCall()
//                val secondAnswer = networkCall2()
                val answer1 = networkCall()
                val answer2 = networkCall2()
                Log.d(TAG, "Ответ: $answer1")
                Log.d(TAG, "Ответ2: $answer2")
            }
            Log.d(TAG, "Запрос: $time ms.")
        }
    }
    suspend fun networkCall(): String{
        delay(3000L)
        return "Ответ 1"
    }

    suspend fun networkCall2(): String{
        delay(3000L)
        return "Ответ 2"
    }


}