package com.shengshijie.hlog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shengshijie.bright.InputDialog
import com.shengshijie.log.HLog
import timber.log.Timber
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HLog.init(application, "RFT")
    }

    fun test(view: View) {
        thread {
            for (i in 1..100) {
                Timber.i("init")
            }

        }
    }

    override fun onDestroy() {
        HLog.destroy()
        super.onDestroy()
    }

}
