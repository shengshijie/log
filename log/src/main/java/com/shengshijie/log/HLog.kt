package com.shengshijie.log

import android.content.Context

object HLog {

    private var mLoggable: Boolean = true

    private var mLog: ILog = LogbackImpl()

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

    private fun getTag(tag: Any?): String {
        if (tag == null) {
            return "<DEBUG> ${Utils.getCallerName()}"
        }
        return when (tag) {
            is String -> {
                "<${tag}> ${Utils.getCallerName()}"
            }
            else -> {
                "<${tag.javaClass.simpleName}> ${Utils.getCallerName()}"
            }
        }
    }

    fun destroy() {
        mLog.destroy()
    }

}