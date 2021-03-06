package com.shengshijie.log

import android.content.Context

interface ILog {

    fun init(context: Context, dir: String?, name: String?)

    fun v(tag: String?, msg: String)

    fun d(tag: String?, msg: String)

    fun i(tag: String?, msg: String)

    fun w(tag: String?, msg: String)

    fun e(tag: String?, msg: String)

    fun destroy()

}