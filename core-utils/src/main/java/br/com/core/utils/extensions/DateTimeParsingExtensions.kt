package br.com.core.utils.extensions

import br.com.core.utils.enums.EnumDateTimePatterns
import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Converte uma [String] em um [LocalDate] utilizando o padrão especificado.
 * Retorna `null` se a string estiver vazia ou se o parsing falhar.
 *
 * @receiver A string contendo a data a ser convertida.
 * @param enumDateTimePatterns O padrão de formato para a conversão.
 * @return O [LocalDate] resultante ou `null`.
 * @author Nikolas Luiz Schmitt
 */
fun String.parseToLocalDate(enumDateTimePatterns: EnumDateTimePatterns): LocalDate? {
    if (this.isEmpty()) return null

    return try {
        LocalDate.parse(this, DateTimeFormatter.ofPattern(enumDateTimePatterns.pattern))
    } catch (_: DateTimeException) {
        null
    }
}

/**
 * Converte uma [String] em um [LocalTime] utilizando o padrão especificado.
 * Retorna `null` se a string estiver vazia ou se o parsing falhar.
 *
 * @receiver A string contendo a hora a ser convertida.
 * @param enumDateTimePatterns O padrão de formato para a conversão.
 * @return O [LocalTime] resultante ou `null`.
 * @author Nikolas Luiz Schmitt
 */
fun String.parseToLocalTime(enumDateTimePatterns: EnumDateTimePatterns): LocalTime? {
    if (this.isEmpty()) return null

    return try {
        LocalTime.parse(this, DateTimeFormatter.ofPattern(enumDateTimePatterns.pattern))
    } catch (_: DateTimeException) {
        null
    }
}

/**
 * Converte uma [String] em um [LocalDateTime] utilizando o padrão especificado.
 * Retorna `null` se a string estiver vazia ou se o parsing falhar.
 *
 * @receiver A string contendo a data e hora a ser convertida.
 * @param enumDateTimePatterns O padrão de formato para a conversão.
 * @return O [LocalDateTime] resultante ou `null`.
 * @author Nikolas Luiz Schmitt
 */
fun String.parseToLocalDateTime(enumDateTimePatterns: EnumDateTimePatterns): LocalDateTime? {
    if (this.isEmpty()) return null

    return try {
        LocalDateTime.parse(this, DateTimeFormatter.ofPattern(enumDateTimePatterns.pattern))
    } catch (_: DateTimeException) {
        null
    }
}

/**
 * Converte uma [String] de hora em um [OffsetDateTime], combinando-a com uma data fornecida.
 * Retorna `null` se a string estiver vazia ou se o parsing falhar.
 *
 * @receiver A string contendo a hora a ser convertida.
 * @param date A [LocalDate] a ser usada como base.
 * @param enumDateTimePatterns O padrão de formato para a conversão da hora.
 * @return O [OffsetDateTime] resultante ou `null`.
 */
fun String.parseTimeToOffsetDateTime(date: LocalDate, enumDateTimePatterns: EnumDateTimePatterns): OffsetDateTime? {
    if (this.isEmpty()) return null
    val localTime = parseToLocalTime(enumDateTimePatterns) ?: return null
    return date.getOffsetDateTime(localTime)
}