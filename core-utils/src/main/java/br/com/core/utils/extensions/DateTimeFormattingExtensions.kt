package br.com.core.utils.extensions

import br.com.core.utils.enums.EnumDateTimePatterns
import java.time.DayOfWeek
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.Locale

/**
 * Formata um [LocalDate] em uma [String] utilizando o padrão especificado.
 *
 * @receiver O [LocalDate] a ser formatado.
 * @param enumDateTimePatterns O padrão de formato para a formatação.
 * @return A [String] formatada.
 */
fun LocalDate.format(enumDateTimePatterns: EnumDateTimePatterns): String {
    return this.format(DateTimeFormatter.ofPattern(enumDateTimePatterns.pattern))
}

/**
 * Formata um [LocalTime] em uma [String] utilizando o padrão especificado.
 *
 * @receiver O [LocalTime] a ser formatado.
 * @param enumDateTimePatterns O padrão de formato para a formatação.
 * @return A [String] formatada.
 */
fun LocalTime.format(enumDateTimePatterns: EnumDateTimePatterns): String {
    return this.format(DateTimeFormatter.ofPattern(enumDateTimePatterns.pattern))
}

/**
 * Formata um [LocalDateTime] em uma [String] utilizando o padrão especificado.
 *
 * @receiver O [LocalDateTime] a ser formatado.
 * @param enumDateTimePatterns O padrão de formato para a formatação.
 * @return A [String] formatada.
 * @author Nikolas Luiz Schmitt
 */
fun LocalDateTime.format(enumDateTimePatterns: EnumDateTimePatterns): String {
    return this.format(DateTimeFormatter.ofPattern(enumDateTimePatterns.pattern))
}

/**
 * Formata um [Instant] em uma [String] utilizando o padrão e a zona do sistema.
 *
 * @receiver O [Instant] a ser formatado.
 * @param enumDateTimePatterns O padrão de formato para a formatação.
 * @return A [String] formatada.
 */
fun Instant.format(enumDateTimePatterns: EnumDateTimePatterns): String {
    return this.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(enumDateTimePatterns.pattern))
}

/**
 * Formata um [OffsetDateTime] em uma [String].
 *
 * @receiver O [OffsetDateTime] a ser formatado.
 * @param pattern O padrão de formato [EnumDateTimePatterns] a ser usado.
 * @param zoneId Uma [ZoneId] opcional para converter o tempo antes de formatar.
 * @return A [String] formatada.
 */
fun OffsetDateTime.format(pattern: EnumDateTimePatterns, zoneId: ZoneId? = null): String {
    val formatter = DateTimeFormatter.ofPattern(pattern.pattern)
    return if (zoneId != null) {
        this.atZoneSameInstant(zoneId).format(formatter)
    } else {
        this.format(formatter)
    }
}

/**
 * Formata um [YearMonth] em uma [String] utilizando o padrão especificado.
 *
 * @receiver O [YearMonth] a ser formatado.
 * @param enumDateTimePatterns O padrão de formato para a formatação.
 * @return A [String] formatada.
 */
fun YearMonth.format(enumDateTimePatterns: EnumDateTimePatterns): String {
    return this.format(DateTimeFormatter.ofPattern(enumDateTimePatterns.pattern))
}

/**
 * Formata uma [Duration] no formato "HH:mm:ss".
 *
 * @receiver A [Duration] a ser formatada.
 * @return A [String] formatada, por exemplo, "01:30:05".
 */
fun Duration.formatSimpleTime(): String {
    val totalSeconds = this.seconds
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return "%02d:%02d:%02d".format(hours, minutes, seconds)
}

/**
 * Retorna o nome de exibição completo de um [DayOfWeek] (ex: "Segunda-feira" -> "Segunda").
 * A primeira letra é capitalizada.
 *
 * @receiver O [DayOfWeek] a ser formatado.
 * @return A primeira parte do nome completo do dia.
 */
fun DayOfWeek.getFirstPartFullDisplayName(): String {
    val displayName = getDisplayName(TextStyle.FULL, Locale.getDefault())
    val firstPart = displayName.split("-")[0]
    return firstPart.replaceFirstChar(Char::uppercase)
}

/**
 * Retorna o nome de exibição curto de um [DayOfWeek] em maiúsculas (ex: "seg." -> "SEG").
 *
 * @receiver O [DayOfWeek] a ser formatado.
 * @return A abreviação do dia em maiúsculas.
 */
fun DayOfWeek.getShortDisplayNameAllCaps(): String {
    return getDisplayName(TextStyle.SHORT, Locale.getDefault()).uppercase().replace(".", "")
}

/**
 * Retorna o nome de exibição curto de um [DayOfWeek] com a primeira letra maiúscula (ex: "seg." -> "Seg").
 *
 * @receiver O [DayOfWeek] a ser formatado.
 * @return A abreviação do dia capitalizada.
 */
fun DayOfWeek.getShortDisplayName(): String {
    val displayName = getDisplayName(TextStyle.SHORT, Locale.getDefault()).replace(".", "")
    return displayName.replaceFirstChar(Char::uppercase)
}

/**
 * Formata um valor numérico representando uma unidade de tempo [ChronoUnit] para [String].
 * Retorna uma string vazia se o valor ou a unidade forem nulos.
 *
 * @receiver O [ChronoUnit] que define a conversão. Pode ser nulo.
 * @param value O valor em milissegundos a ser convertido e formatado.
 * @return A [String] com o valor convertido, ou `""`.
 */
fun ChronoUnit?.getStringFromConvertedChronoUnitValue(value: Long?): String {
    return this?.let { value?.millisTo(it) }.toStringOrEmpty()
}