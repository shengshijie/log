package com.shengshijie.log

import org.slf4j.LoggerFactory

object Test {

    private var log = LoggerFactory.getLogger(Test::class.java)

    private val keymap: Map<Int, () -> Unit> = mapOf(
            1 to { log.debug("start server at 4568") },
            2 to { log.debug("stop server at 4568") }
    )

    fun process(code: Int) = keymap[code]?.invoke()

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        process(1)
        while (true);
    }

}
