package com.shengshijie.log

sealed class LogType {

    override fun toString(): String {
        return javaClass.simpleName
    }

}

object USR : LogType()//user action
object DVC : LogType()//device
object NET : LogType()//network
object CRS : LogType()//crash
object BSN : LogType()//business
object DBG : LogType()//debug

