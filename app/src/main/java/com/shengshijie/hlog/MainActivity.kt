package com.shengshijie.hlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shengshijie.log.LogInit
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogInit().init(application,"RFT")
    }

    fun test(view: View) {
        Timber.e("ee")
    }

}
