package org.example.thelovers.core.presentation

import org.example.thelovers.core.domain.DataError
import theloversmobileweb.composeapp.generated.resources.*

fun DataError.toUiText(): UiText {
    val stringRes = when(this) {
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_unknown
        DataError.Local.TOKEN_NOT_FOUND -> Res.string.error_token_not_found
        DataError.Local.TOKEN_DECRYPTION_FAILED -> Res.string.error_decryption_failed
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER -> Res.string.error_unknown
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
        DataError.Remote.DENIED -> Res.string.error_request_denied
        DataError.Remote.INVALID_PHONE_NUMBER -> Res.string.error_invalid_phone_number
        DataError.Local.TOKEN_SAVE_FAILED -> Res.string.error_token_saved_failed
        DataError.Local.TOKEN_CLEAR_FAILED -> Res.string.error_token_clear_failed
    }

    return UiText.StringResourceId(stringRes)
}