package com.shengshijie.log

import android.content.Context
import com.orhanobut.logger.*

class LoggerImpl : ILog {

    override fun init(context: Context, dir: String?, name: String?) {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(BuildConfig.DEBUG)
            .methodCount(if (BuildConfig.DEBUG) 1 else 0)
            .methodOffset(5)
            .tag(name)
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
//        val diskFormatStrategy: FormatStrategy = CsvFormatStrategy.newBuilder()
//            .build()
//        val diskLogAdapter = DiskLogAdapter(diskFormatStrategy)
//        Logger.addLogAdapter(diskLogAdapter)
    }

    override fun v(tag: String?, msg: String) {
        Logger.t(tag).v(msg)
    }

    override fun d(tag: String?, msg: String) {
        Logger.t(tag).d(msg)
    }

    override fun i(tag: String?, msg: String) {
        Logger.t(tag).i(msg)
    }

    override fun w(tag: String?, msg: String) {
        Logger.t(tag).w(msg)
    }

    override fun e(tag: String?, msg: String) {
        Logger.t(tag).e(msg)
    }

    override fun destroy() {

    }

}