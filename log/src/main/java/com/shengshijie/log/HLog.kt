package com.shengshijie.log

import android.content.Context

object HLog : ILog {

    var loggable: Boolean = true

    private var log: ILog = LogbackImpl()

    private var mTag: String = ""

    fun setLogImpl(log: ILog) {
        this.log = log
    }

    override fun init(context: Context, dir: String?, tag: String?) {
        this.mTag = tag ?: ""
        CrashUtils.init(context, dir, null)
        log.init(context, dir, tag)
    }

    fun verbose(msg: String, logType: LogType = DBG) {
        v("<${logType.name()}>", msg)
    }

    fun debug(msg: String, logType: LogType = DBG) {
        d("<${logType.name()}>", msg)
    }

    fun info(msg: String, logType: LogType = DBG) {
        i("<${logType.name()}>", msg)
    }

    fun warn(msg: String, logType: LogType = DBG) {
        w("<${logType.name()}>", msg)
    }

    fun error(msg: String, logType: LogType = DBG) {
        e("<${logType.name()}>", msg)
    }

    fun string(any: Any, logType: LogType = DBG) {
        s("<${logType.name()}>", any)
    }

    fun json(msg: String, logType: LogType = DBG) {
        j("<${logType.name()}>", msg)
    }

    fun xml(msg: String, logType: LogType = DBG) {
        x("<${logType.name()}>", msg)
    }

    fun s(tag: String?, any: Any) {
        if (loggable) {
            log.d(tag, Utils.toString(any))
        }
    }

    fun j(tag: String?, msg: String) {
        if (loggable) {
            log.d(tag, Utils.json(msg))
        }
    }

    fun x(tag: String?, msg: String) {
        if (loggable) {
            log.d(tag, Utils.xml(msg))
        }
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