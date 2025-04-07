package org.example.thelovers.core.presentation

import org.example.thelovers.core.domain.DataError
import thelovers.composeapp.generated.resources.Res

//fun DataError.toUiText(): UiText {
//    val stringRes = when(this) {
//        DataError.Local.DISK_FULL -> Res.string.
//        DataError.Local.UNKNOWN -> Res.string.error_unknown
//        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
//        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
//        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
//        DataError.Remote.SERVER -> Res.string.error_unknown
//        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
//        DataError.Remote.UNKNOWN -> Res.string.error_unknown
//    }
//
//    return UiText.StringResourceId(stringRes)
//}