package org.example.thelovers.core.presentation

import org.example.thelovers.core.domain.DataError
import theloversmobileweb.composeapp.generated.resources.*


fun DataError.toUiText(): UiText {
    val stringRes = when(this) {
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_unknown
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER -> Res.string.error_unknown
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
        DataError.Remote.DENIED -> Res.string.error_request_denied
        DataError.Remote.INVALID_PHONE_NUMBER -> Res.string.error_invalid_phone_number
    }

    return UiText.StringResourceId(stringRes)
}