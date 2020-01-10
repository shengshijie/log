package com.shengshijie.log

import android.content.Context
import android.util.Log
import com.shengshijie.log.LogLevel.ALL
import com.shengshijie.log.LogLevel.DEBUG
import com.shengshijie.log.LogLevel.ERROR
import com.shengshijie.log.LogLevel.INFO
import com.shengshijie.log.LogLevel.VERBOSE
import com.shengshijie.log.LogLevel.WARN

object HLog {

    private var mDepth: Int = 3

    private var mLog: ILog = LogcatImpl()

    private var mLevel: Int = ALL

    @JvmStatic
    fun setDepth(depth: Int) {
        mDepth = depth
    }

    @JvmStatic
    fun setLogImpl(log: ILog) {
        mLog = log
    }

    @JvmStatic
    fun setLevel(@LogLevel.Level level: Int) {
        mLevel = level
    }

    @JvmStatic
    @JvmOverloads
    fun init(context: Context, name: String?, dir: String? = null) {
        dir?.apply { CrashUtils.init(context, this, null) }
        mLog.init(context, dir, name)
    }

    @JvmStatic
    @JvmOverloads
    fun e(thr: Throwable, tag: Any? = null) {
        if (mLevel <= ERROR) {
            mLog.e(getTag(tag), Utils.ex(thr))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun s(any: Any?, tag: Any? = null) {
        if (mLevel <= DEBUG) {
            mLog.d(getTag(tag), Utils.toString(any))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun j(msg: String?, tag: Any? = null) {
        if (mLevel <= DEBUG) {
            mLog.d(getTag(tag), Utils.json(msg))
        }
    }

    @JvmStatic
    fun x(msg: String?, tag: Any? = null) {
        if (mLevel <= DEBUG) {
            mLog.d(getTag(tag), Utils.xml(msg))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun v(msg: String?, tag: Any? = null) {
        if (mLevel <= VERBOSE) {
            mLog.v(getTag(tag), msg ?: "null")
        }
    }

    @JvmStatic
    @JvmOverloads
    fun d(msg: String?, tag: Any? = null) {
        if (mLevel <= DEBUG) {
            mLog.d(getTag(tag), msg ?: "null")
        }
    }

    @JvmStatic
    @JvmOverloads
    fun i(msg: String?, tag: Any? = null) {
        if (mLevel <= INFO) {
            mLog.i(getTag(tag), msg ?: "null")
        }
    }

    @JvmStatic
    @JvmOverloads
    fun w(msg: String?, tag: Any? = null) {
        if (mLevel <= WARN) {
            mLog.w(getTag(tag), msg ?: "null")
        }
    }

    @JvmStatic
    @JvmOverloads
    fun e(msg: String?, tag: Any? = null) {
        if (mLevel <= ERROR) {
            mLog.e(getTag(tag), msg ?: "null")
        }
    }

    @JvmStatic
    @JvmOverloads
    fun log(level: Int, msg: String?, tag: Any? = null) {
        if (mLevel <= level) {
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

    @JvmStatic
    fun destroy() {
        mLog.destroy()
    }

    @JvmStatic
    fun getTag(tag: Any?): String {
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

}