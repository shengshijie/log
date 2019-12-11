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

    fun v(msg: String?, logType: LogType = DBG) {
        v("<${logType.name()}>", msg ?: "null")
    }

    fun d(msg: String?, logType: LogType = DBG) {
        d("<${logType.name()}>", msg ?: "null")
    }

    fun i(msg: String?, logType: LogType = DBG) {
        i("<${logType.name()}>", msg ?: "null")
    }

    fun w(msg: String?, logType: LogType = DBG) {
        w("<${logType.name()}>", msg ?: "null")
    }

    fun e(msg: String?, logType: LogType = DBG) {
        e("<${logType.name()}>", msg ?: "null")
    }

    fun s(any: Any?, logType: LogType = DBG) {
        s("<${logType.name()}>", any ?: "null")
    }

    fun j(msg: String?, logType: LogType = DBG) {
        j("<${logType.name()}>", msg ?: "null")
    }

    fun x(msg: String?, logType: LogType = DBG) {
        x("<${logType.name()}>", msg ?: "null")
    }

    fun s(tag: String?, any: Any?) {
        if (loggable) {
            log.d(tag, Utils.toString(any))
        }
    }

    fun j(tag: String?, msg: String?) {
        if (loggable) {
            log.d(tag, Utils.json(msg))
        }
    }

    fun x(tag: String?, msg: String?) {
        if (loggable) {
            log.d(tag, Utils.xml(msg))
        }
    }

    fun v(tag: String?, msg: String?) {
        if (loggable) {
            log.v(tag, msg ?: "null")
        }
    }

    fun d(tag: String?, msg: String?) {
        if (loggable) {
            log.d(tag, msg ?: "null")
        }
    }

    fun i(tag: String?, msg: String?) {
        if (loggable) {
            log.i(tag, msg ?: "null")
        }
    }

    fun w(tag: String?, msg: String?) {
        if (loggable) {
            log.w(tag, msg ?: "null")
        }
    }

    fun e(tag: String?, msg: String?) {
        if (loggable) {
            log.e(tag, msg ?: "null")
        }
    }

    fun destroy() {
        log.destroy()
    }

}