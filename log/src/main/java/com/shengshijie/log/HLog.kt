package com.shengshijie.log

import android.content.Context
import timber.log.Timber

object HLog : ILog {

    var loggable: Boolean = true

    var log: ILog = LogbackImpl()

    fun setLogImpl(log: ILog) {
        this.log = log
    }

    override fun init(context: Context, dir: String?, tag: String?) {
        CrashUtils.init(context, dir,null)
        log.init(context, dir, tag)
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, tag, message, t)
                when (priority) {
                    android.util.Log.VERBOSE -> if (BuildConfig.DEBUG) HLog.v(tag, message)
                    android.util.Log.DEBUG -> if (BuildConfig.DEBUG) HLog.d(tag, message)
                    android.util.Log.INFO -> HLog.i(tag, message)
                    android.util.Log.WARN -> HLog.w(tag, message)
                    android.util.Log.ERROR -> HLog.e(tag, message)
                }
            }
        })
    }

    override fun v(tag: String?, msg: String) {
        if (loggable) {
            log.v(tag, msg)
        }
    }

    override fun d(tag: String?, msg: String) {
        if (loggable) {
            log.d(tag, msg)
        }
    }

    override fun i(tag: String?, msg: String) {
        if (loggable) {
            log.i(tag, msg)
        }
    }

    override fun w(tag: String?, msg: String) {
        if (loggable) {
            log.w(tag, msg)
        }
    }

    override fun e(tag: String?, msg: String) {
        if (loggable) {
            log.e(tag, msg)
        }
    }

    override fun destroy() {
        log.destroy()
    }

}