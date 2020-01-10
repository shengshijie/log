package com.shengshijie.log

import androidx.annotation.IntDef

object LogLevel {

    const val ALL = 0
    const val VERBOSE = 2
    const val DEBUG = 3
    const val INFO = 4
    const val WARN = 5
    const val ERROR = 6
    const val OFF = 7

    @IntDef(ALL, VERBOSE, DEBUG, INFO, WARN, ERROR, OFF)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Level

}