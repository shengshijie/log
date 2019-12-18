package com.shengshijie.log

import android.content.Context
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog

class XLogImpl : ILog {

    override fun init(context: Context, dir: String?, name: String?) {
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
        dir?.apply {
            Xlog.appenderOpen(
                Xlog.LEVEL_ALL,
                Xlog.AppednerModeAsync,
                this,
                this,
                name,
                31,
                ""
            )
        }
        Xlog.setConsoleLogOpen(true)
        Log.setLogImp(Xlog())
    }

    override fun v(tag: String?, msg: String) {
        Log.v(tag, msg)
    }

    override fun d(tag: String?, msg: String) {
        Log.d(tag, msg)
    }

    override fun i(tag: String?, msg: String) {
        Log.i(tag, msg)
    }

    override fun w(tag: String?, msg: String) {
        Log.w(tag, msg)
    }

    override fun e(tag: String?, msg: String) {
        Log.e(tag, msg)
    }

    override fun destroy() {
        Log.appenderClose()
    }

}