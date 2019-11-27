package com.shengshijie.log

import android.app.Application
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog
import timber.log.Timber

class LogInit {

    init {
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
    }

    fun init(application: Application, prefix: String) {
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
                    Xlog.LEVEL_VERBOSE -> if (BuildConfig.DEBUG) Log.v(tag, message)
                    Xlog.LEVEL_DEBUG -> if (BuildConfig.DEBUG) Log.d(tag, message)
                    Xlog.LEVEL_INFO -> Log.i(tag, message)
                    Xlog.LEVEL_WARNING -> Log.w(tag, message)
                    Xlog.LEVEL_ERROR -> Log.e(tag, message)
                }
            }
        })
    }

}