package br.com.core.utils.extensions

import java.text.DecimalFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

/**
 * Formata um [Double] para uma [String] utilizando as configurações de Locale do dispositivo.
 * Em caso de erro, retorna uma string vazia.
 *
 * @receiver O [Double] a ser formatado. Pode ser nulo.
 * @return A [String] formatada ou `""` em caso de falha.
 */
fun Double?.formatToDecimal(): String {
    return try {
        DecimalFormat.getInstance().format(this)
    } catch (_: Exception) {
        ""
    }
}

/**
 * Converte um valor [Long] representando milissegundos desde a epoch para um [LocalDate].
 *
 * @receiver O valor em milissegundos.
 * @return O [LocalDate] correspondente na zona de sistema padrão.
 */
fun Long.toLocalDate(): LocalDate {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()
}

/**
 * Converte um valor [Long] representando milissegundos desde a epoch para um [LocalDateTime].
 *
 * @receiver O valor em milissegundos.
 * @return O [LocalDateTime] correspondente na zona de sistema padrão.
 */
fun Long.toLocalDateTime(): LocalDateTime {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDateTime()
}

/**
 * Converte um valor [Long] em milissegundos para um [ChronoUnit] mais apropriado
 * para exibição (Segundos, Minutos ou Horas).
 *
 * Exemplo:
 * - `5000L.bestChronoUnit()` retorna `ChronoUnit.SECONDS`
 * - `120000L.bestChronoUnit()` retorna `ChronoUnit.MINUTES`
 *
 * @receiver O valor em milissegundos.
 * @return O [ChronoUnit] mais adequado para representar a duração.
 */
fun Long.bestChronoUnit(): ChronoUnit {
    val secondLimit = 59 * ChronoUnit.SECONDS.duration.toMillis()
    val minuteLimit = 59 * ChronoUnit.MINUTES.duration.toMillis()
    val hourLimit = 23 * ChronoUnit.HOURS.duration.toMillis()

    return when {
        this <= secondLimit -> ChronoUnit.SECONDS
        this <= minuteLimit -> ChronoUnit.MINUTES
        this <= hourLimit -> ChronoUnit.HOURS
        else -> ChronoUnit.HOURS
    }
}

/**
 * Converte um valor em milissegundos para uma determinada unidade de tempo [ChronoUnit].
 *
 * @receiver O valor em milissegundos a ser convertido.
 * @param unit A [ChronoUnit] de destino.
 * @throws IllegalArgumentException se a unidade de destino tiver duração zero.
 * @return O valor convertido na unidade de tempo especificada.
 */
fun Long.millisTo(unit: ChronoUnit): Long {
    require(unit.duration.toMillis() != 0L) { "Valor de unidade inválido: $unit" }
    return this / unit.duration.toMillis()
}
