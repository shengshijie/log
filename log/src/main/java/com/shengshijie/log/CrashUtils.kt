package com.shengshijie.log

import android.content.Context
import android.content.pm.PackageInfo
import android.os.Build
import android.os.Debug
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.*

internal object CrashUtils {

    fun init(context: Context, dir: String?, f: ((Throwable) -> Unit)?) {
        val pi: PackageInfo = context.packageManager
            .getPackageInfo(context.packageName, 0)
        val uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        val exceptionHandler = Thread.UncaughtExceptionHandler { t, e ->
            val time = SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.CHINA
            ).format(Date(System.currentTimeMillis()))
            val sb = StringBuilder()
            sb.appendln()
                .appendln("**************************************************")
                .appendln("Time                 :$time")
                .appendln("Device Manufacturer  :${Build.MANUFACTURER}")
                .appendln("Device Model         :${Build.MODEL}")
                .appendln("Android Version      :${Build.VERSION.RELEASE}")
                .appendln("Android SDK          :${Build.VERSION.SDK_INT}")
                .appendln("App VersionName      :${pi.versionName}")
                .appendln("App VersionCode      :${pi.versionCode}")
                .appendln("**************************************************")
                .appendln()
            val sw = StringWriter()
            val pw = PrintWriter(sw)
            e.printStackTrace(pw)
            var cause = e.cause
            while (cause != null) {
                cause.printStackTrace(pw)
                cause = cause.cause
            }
            pw.flush()
            sb.append(sw.toString())
            val crashInfo = sb.toString()
            Debug.dumpHprofData(File(dir, "/memory.hprof").absolutePath)
            File(dir, "crash $time.txt").writeText(crashInfo)
            f?.invoke(e)
            uncaughtExceptionHandler?.uncaughtException(t, e)
        }
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler)
    }

}