package com.shengshijie.log

import android.content.Context
import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.android.LogcatAppender
import ch.qos.logback.classic.android.SQLiteAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.LevelFilter
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.classic.net.SocketAppender
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.spi.FilterReply
import ch.qos.logback.core.util.Duration
import ch.qos.logback.core.util.StatusPrinter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

class LogbackImpl : ILog {

    var logcat: Boolean = true

    var file: Boolean = false

    var db: Boolean = false

    var socket: Boolean = false

    var socketHost: String = ""

    var socketPort: Int = 0

    override fun init(context: Context, dir: String?, name: String?) {
        configureLogbackDirectly(dir)
    }

    override fun v(tag: String?, msg: String) {
        LoggerFactory.getLogger(tag)?.trace(msg)
    }

    override fun d(tag: String?, msg: String) {
        LoggerFactory.getLogger(tag)?.debug(msg)
    }

    override fun i(tag: String?, msg: String) {
        LoggerFactory.getLogger(tag)?.info(msg)
    }

    override fun w(tag: String?, msg: String) {
        LoggerFactory.getLogger(tag)?.warn(msg)
    }

    override fun e(tag: String?, msg: String) {
        LoggerFactory.getLogger(tag)?.error(msg)
    }

    override fun destroy() {
        (LoggerFactory.getILoggerFactory() as LoggerContext).stop()
    }

    private fun configureLogbackDirectly(dir: String?) {
        val lc = LoggerFactory.getILoggerFactory() as LoggerContext
        lc.stop()
        (LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as ch.qos.logback.classic.Logger).apply {
            level = HLog.getLevel().toLogBackLevel()
            if (logcat) {
                addAppender(LogcatAppender().apply {
                    context = lc
                    encoder = PatternLayoutEncoder().apply {
                        context = lc
                        pattern = "[%thread] %msg%n"
                        start()
                    }
                    start()
                })
            }
            if (dir != null && db) {
                addAppender(SQLiteAppender().apply {
                    maxHistory = "31 days"
                    filename = dir + File.separatorChar + "log.db"
                    start()
                })
            }
            if (dir != null && file) {
                addAppender(RollingFileAppender<ILoggingEvent>().apply fileAppender@{
                    isAppend = true
                    context = lc
                    rollingPolicy = TimeBasedRollingPolicy<ILoggingEvent>().apply {
                        fileNamePattern = dir + File.separatorChar + "%d{yyyy-MM, aux}/%d.log"
                        maxHistory = 31
                        context = lc
                        setParent(this@fileAppender)
                        start()
                    }
                    encoder = PatternLayoutEncoder().apply {
                        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg%n"
                        context = lc
                        start()
                    }
                    start()
                })
            }
            if (socket) {
                val socketAppender = SocketAppender().apply {
                    context = lc
                    remoteHost = socketHost
                    port = socketPort
                    reconnectionDelay = Duration(10000)
                    start()
                }
                addAppender(AsyncAppender().apply {
                    context = lc
                    addAppender(socketAppender)
                    start()
                })
            }
        }
        StatusPrinter.print(lc)
    }

}