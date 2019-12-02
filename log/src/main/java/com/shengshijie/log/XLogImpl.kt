package com.shengshijie.log

import android.content.Context
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog

class XLogImpl : ILog {

    override fun init(context: Context, dir: String?, tag: String?) {
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
        Xlog.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, dir, dir, tag, 31, "")
        Xlog.setConsoleLogOpen(false)
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