package com.shengshijie.log

import android.content.Context
import android.util.Log

class StdoutImpl : ILog {

    override fun init(context: Context, dir: String?, name: String?) {

    }

    override fun v(tag: String?, msg: String) {
        println("$tag $msg")
    }

    override fun d(tag: String?, msg: String) {
        println("$tag $msg")
    }

    override fun i(tag: String?, msg: String) {
        println("$tag $msg")
    }

    override fun w(tag: String?, msg: String) {
        println("$tag $msg")
    }

    override fun e(tag: String?, msg: String) {
        println("$tag $msg")
    }

    override fun destroy() {

    }

}