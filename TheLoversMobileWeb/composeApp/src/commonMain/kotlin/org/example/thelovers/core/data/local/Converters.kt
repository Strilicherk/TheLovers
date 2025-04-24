package org.example.thelovers.core.data.local

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class Converters {
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.toString() // A conversão para String é bem simples
    }

    @TypeConverter
    fun toLocalDate(date: String?): LocalDate? {
        return date?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? {
        return dateTime?.toString() // Usando o padrão ISO 8601
    }

    @TypeConverter
    fun toLocalDateTime(dateTime: String?): LocalDateTime? {
        return dateTime?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun fromUuid(value: Uuid?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toUuid(value: String?): Uuid? {
        return value?.let { Uuid.parse(it) }
    }

    @TypeConverter
    fun fromTimestampToLocalDateTime(date: Long): LocalDateTime {
        return date.let {
            Instant.fromEpochMilliseconds(it)
                .toLocalDateTime(TimeZone.currentSystemDefault())
        }
    }

    @TypeConverter
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