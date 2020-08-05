package com.shengshijie.log

import android.util.Log
import ch.qos.logback.classic.Level

enum class HLogLevel(private val androidLevel: Int, private val logbackLevel: Level) {

    TRACE(Log.VERBOSE, Level.TRACE),
    DEBUG(Log.DEBUG, Level.DEBUG),
    INFO(Log.INFO, Level.INFO),
    WARN(Log.WARN, Level.WARN),
    ERROR(Log.ERROR, Level.ERROR);

    fun toAndroidLogLevel(): Int {
        return androidLevel
    }

    fun toLogBackLevel(): Level {
        return logbackLevel
    }

}