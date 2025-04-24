package org.example.thelovers.core.logger

class JsLogger : Logger {
    override fun log(tag: String, message: String) {
        console.log("[$tag] $message")
    }

    override fun error(tag: String, message: String, throwable: Throwable?) {
        console.error("[$tag] $message", throwable)
    }
}

actual fun getLogger(): Logger = JsLogger()