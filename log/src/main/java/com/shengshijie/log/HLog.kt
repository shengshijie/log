package com.shengshijie.log

import android.content.Context

object HLog {

    var loggable: Boolean = true

    private var log: ILog = LogbackImpl()

    private var mTag: String = ""

    fun setLogImpl(log: ILog) {
        this.log = log
    }

    fun init(context: Context, dir: String?, tag: String?) {
        this.mTag = tag ?: ""
        CrashUtils.init(context, dir, null)
        log.init(context, dir, tag)
    }

    fun e(thr: Throwable) {
        if (loggable) {
            log.e(getTag(null), Utils.ex(thr))
        }
    }

    fun s(any: Any?) {
        if (loggable) {
            log.d(getTag(null), Utils.toString(any))
        }
    }

    fun j(msg: String?) {
        if (loggable) {
            log.d(getTag(null), Utils.json(msg))
        }
    }

    fun x(msg: String?) {
        if (loggable) {
            log.d(getTag(null), Utils.xml(msg))
        }
    }

    fun v(msg: String?) {
        if (loggable) {
            log.v(getTag(null), msg ?: "null")
        }
    }

    fun d(msg: String?) {
        if (loggable) {
            log.d(getTag(null), msg ?: "null")
        }
    }

    fun i(msg: String?) {
        if (loggable) {
            log.i(getTag(null), msg ?: "null")
        }
    }

    fun w(msg: String?) {
        if (loggable) {
            log.w(getTag(null), msg ?: "null")
        }
    }

    fun e(msg: String?) {
        if (loggable) {
            log.e(getTag(null), msg ?: "null")
        }
    }

    fun e(thr: Throwable, tag: Any) {
        if (loggable) {
            log.e(getTag(tag), Utils.ex(thr))
        }
    }

    fun s(any: Any?, tag: Any) {
        if (loggable) {
            log.d(getTag(tag), Utils.toString(any))
        }
    }

    fun j(msg: String?, tag: Any) {
        if (loggable) {
            log.d(getTag(tag), Utils.json(msg))
        }
    }

    fun x(msg: String?, tag: Any) {
        if (loggable) {
            log.d(getTag(tag), Utils.xml(msg))
        }
    }

    fun v(msg: String?, tag: Any) {
        if (loggable) {
            log.v(getTag(tag), msg ?: "null")
        }
    }

    fun d(msg: String?, tag: Any) {
        if (loggable) {
            log.d(getTag(tag), msg ?: "null")
        }
    }

    fun i(msg: String?, tag: Any) {
        if (loggable) {
            log.i(getTag(tag), msg ?: "null")
        }
    }

    fun w(msg: String?, tag: Any) {
        if (loggable) {
            log.w(getTag(tag), msg ?: "null")
        }
    }

    fun e(msg: String?, tag: Any) {
        if (loggable) {
            log.e(getTag(tag), msg ?: "null")
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
        log.destroy()
    }

}