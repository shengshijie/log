package com.shengshijie.log

import android.content.Context
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.android.LogcatAppender
import ch.qos.logback.classic.android.SQLiteAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.util.StatusPrinter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File


class LogbackImpl : ILog {

    private var log: Logger? = null

    var logcat: Boolean = true

    var file: Boolean = false

    var db: Boolean = false

    override fun init(context: Context, dir: String?, tag: String?) {
        configureLogbackDirectly(dir)
        log = LoggerFactory.getLogger(tag)
    }

    override fun v(tag: String?, msg: String) {
        log?.trace("$tag $msg")
    }

    override fun d(tag: String?, msg: String) {
        log?.debug("$tag $msg")
    }

    override fun i(tag: String?, msg: String) {
        log?.info("$tag $msg")
    }

    override fun w(tag: String?, msg: String) {
        log?.warn("$tag $msg")
    }

    override fun e(tag: String?, msg: String) {
        log?.error("$tag $msg")
    }

    override fun destroy() {

    }

    private fun configureLogbackDirectly(dir: String?) {
        val lc = LoggerFactory.getILoggerFactory() as LoggerContext
        lc.stop()
        val root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as ch.qos.logback.classic.Logger
        root.level = Level.TRACE
        if (logcat) {
            root.addAppender(LogcatAppender().apply {
                val logcatEncoder = PatternLayoutEncoder()
                logcatEncoder.context = lc
                logcatEncoder.pattern = "[%thread] %-5level %logger{35} - %msg%n"
                logcatEncoder.start()
                context = lc
                encoder = logcatEncoder
                start()
            })
            val logcatAppender = LogcatAppender()
            root.addAppender(logcatAppender)
        }
        if (db) {
            root.addAppender(SQLiteAppender().apply {
                filename = dir + File.separatorChar + "logback.db"
                start()
            })
        }
        if (file) {
            root.addAppender(RollingFileAppender<ILoggingEvent>().apply {
                isAppend = true
                context = lc
                val rollingPolicy = TimeBasedRollingPolicy<ILoggingEvent>()
                rollingPolicy.fileNamePattern = dir + File.separatorChar + "%d{yyyy-MM, aux}/%d.log"
                rollingPolicy.maxHistory = 31
                rollingPolicy.setParent(this) // parent and context required!
                rollingPolicy.context = lc
                rollingPolicy.start()
                setRollingPolicy(rollingPolicy)
                val encoder = PatternLayoutEncoder()
                encoder.pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg%n"
                encoder.context = lc
                encoder.start()
                setEncoder(encoder)
                start()
            })
        }
        StatusPrinter.print(lc)
    }

}