package br.com.core.utils.extensions

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Retorna o [LocalTime] atual para uma [ZoneId] específica.
 *
 * @param zoneId A zona de tempo desejada.
 * @return O [LocalTime] atual.
 */
fun timeNow(zoneId: ZoneId): LocalTime {
    return ZonedDateTime.now(zoneId).toLocalTime()
}

/**
 * Retorna o [LocalDate] atual para uma [ZoneId] específica.
 *
 * @param zoneId A zona de tempo desejada.
 * @return O [LocalDate] atual.
 */
fun dateNow(zoneId: ZoneId): LocalDate {
    return ZonedDateTime.now(zoneId).toLocalDate()
}

/**
 * Retorna o [LocalDateTime] atual para uma [ZoneId] específica.
 *
 * @param zoneId A zona de tempo desejada.
 * @return O [LocalDateTime] atual.
 */
fun dateTimeNow(zoneId: ZoneId): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.now(), zoneId)
}

/**
 * Retorna o [YearMonth] atual.
 *
 * @return O [YearMonth] atual.
 */
fun yearMonthNow(): YearMonth {
    return YearMonth.now()
}

/**
 * Retorna o [OffsetDateTime] atual para uma [ZoneId] específica.
 *
 * @param zoneId A zona de tempo desejada.
 * @return O [OffsetDateTime] atual.
 */
fun offsetDateTimeNow(zoneId: ZoneId): OffsetDateTime {
    return OffsetDateTime.now(zoneId)
}

/**
 * Converte um [LocalDateTime] para milissegundos desde a epoch,
 * usando o fuso horário padrão do sistema.
 *
 * @receiver O [LocalDateTime] a ser convertido.
 * @return O valor em milissegundos (`Long`).
 */
fun LocalDateTime.toEpochMillis(): Long {
    val zoneId = ZoneId.systemDefault()
    val zoneOffset = zoneId.rules.getOffset(this)
    return this.toInstant(zoneOffset).toEpochMilli()
}

/**
 * Cria um [OffsetDateTime] a partir de um [LocalDate] e um [LocalTime],
 * utilizando o fuso horário padrão do sistema para calcular o offset.
 *
 * @receiver O [LocalDate] base.
 * @param time O [LocalTime] a ser combinado.
 * @return O [OffsetDateTime] resultante.
 */
fun LocalDate.getOffsetDateTime(time: LocalTime): OffsetDateTime {
    val zoneId = ZoneId.systemDefault()
    val offset = zoneId.rules.getOffset(this.atTime(time))
    return OffsetDateTime.of(this, time, offset)
}