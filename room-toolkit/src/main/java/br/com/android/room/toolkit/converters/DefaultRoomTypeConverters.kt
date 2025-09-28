package br.com.android.room.toolkit.converters

import androidx.room.TypeConverter
import br.com.core.utils.enums.EnumDateTimePatterns
import br.com.core.utils.extensions.format
import br.com.core.utils.extensions.parseToLocalDate
import br.com.core.utils.extensions.parseToLocalDateTime
import br.com.core.utils.extensions.parseToLocalTime
import java.time.DateTimeException
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeParseException
import kotlin.let

/**
 * Fornece um conjunto padrão de [TypeConverter] para o Room, permitindo a persistência
 * de tipos comuns da API `java.time` que não são suportados nativamente.
 *
 * As conversões são feitas de e para representações em [String].
 *
 * @author Nikolas Luiz Schmitt
 */
open class DefaultRoomTypeConverters {
    @TypeConverter
    fun toDateTime(dateString: String?): LocalDateTime? {
        return dateString?.parseToLocalDateTime(EnumDateTimePatterns.DATE_TIME_SQLITE)
    }

    @TypeConverter
    fun toDateTimeString(date: LocalDateTime?): String? {
        return date?.format(EnumDateTimePatterns.DATE_TIME_SQLITE)
    }

    @TypeConverter
    fun toDate(dateString: String?): LocalDate? {
        return dateString?.parseToLocalDate(EnumDateTimePatterns.DATE_SQLITE)
    }

    @TypeConverter
    fun toDateString(date: LocalDate?): String? {
        return date?.format(EnumDateTimePatterns.DATE_SQLITE)
    }

    @TypeConverter
    fun toTime(timeString: String?): LocalTime? {
        return timeString?.parseToLocalTime(EnumDateTimePatterns.TIME)
    }

    @TypeConverter
    fun toTimeString(time: LocalTime?): String? {
        return time?.format(EnumDateTimePatterns.TIME)
    }

    @TypeConverter
    fun fromOffsetDateTime(value: OffsetDateTime?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return try {
            value?.let { OffsetDateTime.parse(it) }
        } catch (e: DateTimeParseException) {
            null
        }
    }

    @TypeConverter
    fun fromZoneOffset(value: ZoneOffset?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toZoneOffset(value: String?): ZoneOffset? {
        return try {
            value?.let { ZoneOffset.of(it) }
        } catch (e: DateTimeException) {
            null
        }
    }

    @TypeConverter
    fun fromInstant(value: Instant?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toInstant(value: String?): Instant? {
        return value?.let { Instant.parse(it) }
    }
}