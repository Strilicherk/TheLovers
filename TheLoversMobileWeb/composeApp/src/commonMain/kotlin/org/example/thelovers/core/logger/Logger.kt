package org.example.thelovers.core.logger

interface Logger {
    fun log(tag: String, message: String)
    fun error(tag: String, message: String, throwable: Throwable? = null)
}

expect fun getLogger(): Logger