package com.shengshijie.log

import android.content.Context
import android.util.Log

object HLog {

    private var mDepth: Int = 4

    private var mLog: ILog = LogcatImpl()

    private var mLevel: HLogLevel = HLogLevel.INFO

    @JvmStatic
    fun setDepth(depth: Int) {
        if (depth <= 4) {
            return
        }
        mDepth = depth
    }

    @JvmStatic
    fun setLogImpl(log: ILog) {
        mLog = log
    }

    @JvmStatic
    fun setLevel(level: HLogLevel) {
        mLevel = level
    }

    @JvmStatic
    fun getLevel(): HLogLevel {
        return mLevel
    }

    @JvmStatic
    @JvmOverloads
    fun init(
        context: Context,
        name: String? = "hLog",
        dir: String? = context.getExternalFilesDir(null)?.absolutePath,
        onCrash: ((Throwable) -> Unit)? = null
    ) {
        dir?.apply { CrashUtils.init(context, this, onCrash) }
        mLog.init(context, dir, name)
    }

    @JvmStatic
    @JvmOverloads
    fun s(any: Any?, tag: Any? = null) {
        if (mLevel <= HLogLevel.DEBUG) {
            mLog.d(getTag(tag), getMessage(tag, Utils.toString(any)))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun j(msg: String?, tag: Any? = null) {
        if (mLevel <= HLogLevel.DEBUG) {
            mLog.d(getTag(tag), getMessage(tag, Utils.json(msg)))
        }
    }

    @JvmStatic
    fun x(msg: String?, tag: Any? = null) {
        if (mLevel <= HLogLevel.DEBUG) {
            mLog.d(getTag(tag), getMessage(tag, Utils.xml(msg)))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun v(msg: String?, tag: Any? = null) {
        if (mLevel <= HLogLevel.TRACE) {
            mLog.v(getTag(tag), getMessage(tag, msg))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun d(msg: String?, tag: Any? = null) {
        if (mLevel <= HLogLevel.DEBUG) {
            mLog.d(getTag(tag), getMessage(tag, msg))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun i(msg: String?, tag: Any? = null) {
        if (mLevel <= HLogLevel.INFO) {
            mLog.i(getTag(tag), getMessage(tag, msg))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun w(msg: String?, tag: Any? = null) {
        if (mLevel <= HLogLevel.WARN) {
            mLog.w(getTag(tag), getMessage(tag, msg))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun e(msg: String?, tag: Any? = null) {
        if (mLevel <= HLogLevel.ERROR) {
            mLog.e(getTag(tag), getMessage(tag, msg))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun e(thr: Throwable, tag: Any? = null) {
        if (mLevel <= HLogLevel.ERROR) {
            mLog.e(getTag(tag), getMessage(tag, Utils.ex(thr)))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun log(level: Int, msg: String?, tag: Any? = null) {
        if (mLevel.toAndroidLogLevel() <= level) {
            when (level) {
                Log.VERBOSE -> {
                    mLog.v(getTag(tag), getMessage(tag, msg))
                }
                Log.DEBUG -> {
                    mLog.d(getTag(tag), getMessage(tag, msg))
                }
                Log.INFO -> {
                    mLog.i(getTag(tag), getMessage(tag, msg))
                }
                Log.WARN -> {
                    mLog.w(getTag(tag), getMessage(tag, msg))
                }
                Log.ERROR -> {
                    mLog.e(getTag(tag), getMessage(tag, msg))
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
            return "<${Utils.getCallerSimpleClassName(mDepth)}>"
        }
        return when (tag) {
            is String -> {
                "<${tag}>"
            }
            else -> {
                "<${tag.javaClass.simpleName}> "
            }
        }
    }

    private fun getMessage(tag: Any?, message: String?): String {
        if (message == null) {
            return "null"
        }
        return "${Utils.getCallerName(if (tag == null) mDepth else (mDepth - 1))} $message"
    }

}