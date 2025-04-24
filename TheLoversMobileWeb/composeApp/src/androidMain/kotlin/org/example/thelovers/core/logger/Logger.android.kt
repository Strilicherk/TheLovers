package org.example.thelovers.core.logger

import android.util.Log

class AndroidLogger : Logger {
    override fun log(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun error(tag: String, message: String, throwable: Throwable?) {
        Log.e(tag, message, throwable)
    }
}

actual fun getLogger(): Logger = AndroidLogger()