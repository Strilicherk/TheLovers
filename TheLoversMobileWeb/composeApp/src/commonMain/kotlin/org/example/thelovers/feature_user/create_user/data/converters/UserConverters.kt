package org.example.thelovers.feature_user.create_user.data.converters

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class UserConverters {
    fun fromTimestampToLocalDateTime(date: Long): LocalDateTime {
        return date.let {
            Instant.fromEpochMilliseconds(it)
                .toLocalDateTime(TimeZone.currentSystemDefault())
        }
    }

    fun fromLocalDateTimeToTimestamp(date: LocalDateTime?): Long {
        return date!!.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }


//    fun fromTimestampWithoutTime(date: Long): LocalDate {
//        return date.let {
//            Instant.ofEpochMilli(it)
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate()
//        }
//    }
//
//
//    fun toTimestampWithoutTime(date: LocalDate): Long {
//        return date.atStartOfDay(ZoneId.systemDefault())
//            .toInstant()
//            .toEpochMilli()
//    }
}