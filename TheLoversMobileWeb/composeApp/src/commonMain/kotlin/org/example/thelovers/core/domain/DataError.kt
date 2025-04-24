package org.example.thelovers.core.domain

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN,
        DENIED,
        INVALID_PHONE_NUMBER
    }

    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN,
        TOKEN_NOT_FOUND,
        TOKEN_DECRYPTION_FAILED,
        TOKEN_SAVE_FAILED,
        TOKEN_CLEAR_FAILED
    }
}