package com.shengshijie.log

import android.annotation.SuppressLint
import android.app.Application
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog
import timber.log.Timber

object HLog {

    init {
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
    }

    @SuppressLint("LogNotTimber")
    fun init(application: Application, prefix: String) {
        android.util.Log.i(javaClass.simpleName, "- - - - - - init - - - - - -")
        val folder = application.getExternalFilesDir(null)?.absolutePath
        Xlog.appenderOpen(
            Xlog.LEVEL_DEBUG,
            Xlog.AppednerModeAsync,
            folder,
            folder,
            prefix,
            31,
            ""
        )
        Xlog.setConsoleLogOpen(false)
        Log.setLogImp(Xlog())
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, tag, message, t)
                when (priority) {
                    android.util.Log.VERBOSE -> if (BuildConfig.DEBUG) Log.v(tag, message)
                    android.util.Log.DEBUG -> if (BuildConfig.DEBUG) Log.d(tag, message)
                    android.util.Log.INFO -> Log.i(tag, message)
                    android.util.Log.WARN -> Log.w(tag, message)
                    android.util.Log.ERROR -> Log.e(tag, message)
                }
            }
        })
    }

    @SuppressLint("LogNotTimber")
    fun destroy() {
        android.util.Log.i(javaClass.simpleName, "- - - - - - destroy - - - - - -")
        Log.appenderClose()
    }

}