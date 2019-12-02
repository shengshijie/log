package com.shengshijie.log

import android.content.Context
import com.orhanobut.logger.*

class LoggerImpl : ILog {

    override fun init(context: Context, prefix: String) {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(BuildConfig.DEBUG)
            .methodCount(if (BuildConfig.DEBUG) 1 else 0)
            .methodOffset(5)
            .tag("---->")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
//        val diskFormatStrategy: FormatStrategy = CsvFormatStrategy.newBuilder()
//            .build()
//        val diskLogAdapter = DiskLogAdapter(diskFormatStrategy)
//        Logger.addLogAdapter(diskLogAdapter)
    }

    override fun v(tag: String?, msg: String) {
        Logger.v(msg)
    }

    override fun d(tag: String?, msg: String) {
        Logger.d(msg)
    }

    override fun i(tag: String?, msg: String) {
        Logger.i(msg)
    }

    override fun w(tag: String?, msg: String) {
        Logger.w(msg)
    }

    override fun e(tag: String?, msg: String) {
        Logger.e(msg)
    }

    override fun destroy() {

    }

}