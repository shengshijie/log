package com.shengshijie.log

import android.content.Context

object HLog : ILog {

    var loggable: Boolean = true

    private var log: ILog = LogbackImpl()

    private var tag: String? = "TAG"

    fun setLogImpl(log: ILog) {
        this.log = log
    }

    override fun init(context: Context, dir: String?, tag: String?) {
        this.tag = tag
        CrashUtils.init(context, dir, null)
        log.init(context, dir, tag)
    }

    fun v(msg: String) {
        v(tag, msg)
    }

    fun d(msg: String) {
        d(tag, msg)
    }

    fun i(msg: String) {
        i(tag, msg)
    }

    fun w(msg: String) {
        w(tag, msg)
    }

    fun e(msg: String) {
        e(tag, msg)
    }

    fun str(any: Any) {
        str(tag, any)
    }

    fun json(msg: String) {
        json(tag, msg)
    }

    fun xml(msg: String) {
        xml(tag, msg)
    }

    fun str(tag: String?, any: Any) {
        if (loggable) {
            log.v(tag, Utils.toString(any))
        }
    }

    fun json(tag: String?, msg: String) {
        if (loggable) {
            log.v(tag, Utils.json(msg))
        }
    }

    fun xml(tag: String?, msg: String) {
        if (loggable) {
            log.v(tag, Utils.xml(msg))
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