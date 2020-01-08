package com.shengshijie.log

import android.content.Context
import android.util.Log

object HLog {

    private var mLoggable: Boolean = true

    private var mDepth: Int = 3

    private var mLog: ILog = LogcatImpl()

    @JvmStatic
    fun setDepth(depth: Int) {
        mDepth = depth
    }

    @JvmStatic
    fun setLoggable(loggable: Boolean) {
        mLoggable = loggable
    }

    @JvmStatic
    fun setLogImpl(log: ILog) {
        this.mLog = log
    }

    @JvmStatic
    fun init(context: Context, name: String?, dir: String? = null) {
        dir?.apply { CrashUtils.init(context, this, null) }
        mLog.init(context, dir, name)
    }

    @JvmStatic
    fun e(thr: Throwable) {
        if (mLoggable) {
            mLog.e(getTag(null), Utils.ex(thr))
        }
    }

    @JvmStatic
    fun s(any: Any?) {
        if (mLoggable) {
            mLog.d(getTag(null), Utils.toString(any))
        }
    }

    @JvmStatic
    fun j(msg: String?) {
        if (mLoggable) {
            mLog.d(getTag(null), Utils.json(msg))
        }
    }

    @JvmStatic
    fun x(msg: String?) {
        if (mLoggable) {
            mLog.d(getTag(null), Utils.xml(msg))
        }
    }

    @JvmStatic
    fun v(msg: String?) {
        if (mLoggable) {
            mLog.v(getTag(null), msg ?: "null")
        }
    }

    @JvmStatic
    fun d(msg: String?) {
        if (mLoggable) {
            mLog.d(getTag(null), msg ?: "null")
        }
    }

    @JvmStatic
    fun i(msg: String?) {
        if (mLoggable) {
            mLog.i(getTag(null), msg ?: "null")
        }
    }

    @JvmStatic
    fun w(msg: String?) {
        if (mLoggable) {
            mLog.w(getTag(null), msg ?: "null")
        }
    }

    @JvmStatic
    fun e(msg: String?) {
        if (mLoggable) {
            mLog.e(getTag(null), msg ?: "null")
        }
    }

    @JvmStatic
    fun e(thr: Throwable, tag: Any) {
        if (mLoggable) {
            mLog.e(getTag(tag), Utils.ex(thr))
        }
    }

    @JvmStatic
    fun s(any: Any?, tag: Any) {
        if (mLoggable) {
            mLog.d(getTag(tag), Utils.toString(any))
        }
    }

    @JvmStatic
    fun j(msg: String?, tag: Any) {
        if (mLoggable) {
            mLog.d(getTag(tag), Utils.json(msg))
        }
    }

    @JvmStatic
    fun x(msg: String?, tag: Any) {
        if (mLoggable) {
            mLog.d(getTag(tag), Utils.xml(msg))
        }
    }

    @JvmStatic
    fun v(msg: String?, tag: Any) {
        if (mLoggable) {
            mLog.v(getTag(tag), msg ?: "null")
        }
    }

    @JvmStatic
    fun d(msg: String?, tag: Any) {
        if (mLoggable) {
            mLog.d(getTag(tag), msg ?: "null")
        }
    }

    @JvmStatic
    fun i(msg: String?, tag: Any) {
        if (mLoggable) {
            mLog.i(getTag(tag), msg ?: "null")
        }
    }

    @JvmStatic
    fun w(msg: String?, tag: Any) {
        if (mLoggable) {
            mLog.w(getTag(tag), msg ?: "null")
        }
    }

    @JvmStatic
    fun e(msg: String?, tag: Any) {
        if (mLoggable) {
            mLog.e(getTag(tag), msg ?: "null")
        }
    }

    @JvmStatic
    fun log(level: Int, msg: String?, tag: Any) {
        if (mLoggable) {
            when (level) {
                Log.VERBOSE -> {
                    mLog.v(getTag(tag), msg ?: "null")
                }
                Log.DEBUG -> {
                    mLog.d(getTag(tag), msg ?: "null")
                }
                Log.INFO -> {
                    mLog.i(getTag(tag), msg ?: "null")
                }
                Log.WARN -> {
                    mLog.w(getTag(tag), msg ?: "null")
                }
                Log.ERROR -> {
                    mLog.e(getTag(tag), msg ?: "null")
                }
            }
        }
    }

    private fun getTag(tag: Any?): String {
        if (tag == null) {
            return "<DEBUG> ${Utils.getCallerName(mDepth)}"
        }
        return when (tag) {
            is String -> {
                "<${tag}> ${Utils.getCallerName(mDepth)}"
            }
            else -> {
                "<${tag.javaClass.simpleName}> ${Utils.getCallerName(mDepth)}"
            }
        }
    }

    fun destroy() {
        mLog.destroy()
    }

}