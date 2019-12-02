package com.shengshijie.log

import android.content.Context
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
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

    override fun init(context: Context, prefix: String) {
        configureLogbackDirectly(context)
        log = LoggerFactory.getLogger(prefix)
    }

    override fun v(tag: String?, msg: String) {
        log?.trace(msg)
    }

    override fun d(tag: String?, msg: String) {
        log?.debug(msg)
    }

    override fun i(tag: String?, msg: String) {
        log?.info(msg)
    }

    override fun w(tag: String?, msg: String) {
        log?.warn(msg)
    }

    override fun e(tag: String?, msg: String) {
        log?.error(msg)
    }

    override fun destroy() {

    }

    private fun configureLogbackDirectly(context: Context) {

        val lc = LoggerFactory.getILoggerFactory() as LoggerContext
        lc.stop()

        val rollingFileAppender = RollingFileAppender<ILoggingEvent>()
        rollingFileAppender.isAppend = true
        rollingFileAppender.context = lc
        val rollingPolicy = TimeBasedRollingPolicy<ILoggingEvent>()
        rollingPolicy.fileNamePattern = context.getExternalFilesDir(null)?.absolutePath + File.separatorChar + "%d{yyyy-MM, aux}/%d.log"
        rollingPolicy.maxHistory = 31
        rollingPolicy.setParent(rollingFileAppender) // parent and context required!
        rollingPolicy.context = lc
        rollingPolicy.start()
        rollingFileAppender.rollingPolicy = rollingPolicy

        val encoder = PatternLayoutEncoder()
        encoder.pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg%n"
        encoder.context = lc
        encoder.start()

        rollingFileAppender.encoder = encoder
        rollingFileAppender.start()

        val root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as ch.qos.logback.classic.Logger
        root.level = Level.TRACE
        root.addAppender(rollingFileAppender)
        StatusPrinter.print(lc)
    }

}