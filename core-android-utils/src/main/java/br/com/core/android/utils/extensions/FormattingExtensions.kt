package br.com.core.android.utils.extensions

import android.content.Context
import android.text.format.Formatter
import br.com.core.android.utils.R
import br.com.core.utils.extensions.bestChronoUnit
import java.time.Duration
import java.time.temporal.ChronoUnit

/**
 * Converte uma duração em milissegundos para uma [String] legível, considerando plurais.
 *
 * Exemplo:
 * - `60000L` -> "1 minuto"
 * - `125000L` -> "2 minutos e 5 segundos"
 *
 * @receiver A duração em milissegundos.
 * @param context O [Context] para acessar os recursos de string (`plurals`).
 * @return A [String] formatada.
 */
fun Long.toReadableDuration(context: Context): String {
    val duration = Duration.ofMillis(this)

    val hours = duration.toHours()
    val minutes = duration.toMinutes() % 60
    val seconds = duration.seconds % 60

    return buildList {
        if (hours > 0) {
            add(context.resources.getQuantityString(R.plurals.label_hours, hours.toInt(), hours))
        }
        if (minutes > 0) {
            add(context.resources.getQuantityString(R.plurals.label_minutes, minutes.toInt(), minutes))
        }
        // Sempre mostra segundos se for a única unidade, ou se for zero mas as outras também são
        if (seconds > 0 || isEmpty()) {
            add(context.resources.getQuantityString(R.plurals.label_seconds, seconds.toInt(), seconds))
        }
    }.joinToString(context.getString(R.string.label_and)) // Junta com " e "
}

/**
 * Converte um tamanho de arquivo em Kilobytes (KB) para um formato legível (ex: "1.2 MB").
 *
 * @receiver O tamanho do arquivo em KB.
 * @param context O [Context] da aplicação.
 * @return A [String] formatada.
 */
fun Long.toReadableFileSize(context: Context): String {
    return Formatter.formatFileSize(context, this * 1024)
}

/**
 * Obtém o rótulo textual para a [ChronoUnit] mais adequada para uma duração em milissegundos.
 *
 * @receiver A duração em milissegundos. Pode ser nulo.
 * @param context O [Context] para acessar os recursos de string.
 * @return O rótulo (ex: "Segundos") ou uma string vazia se o valor for nulo.
 */
fun Long?.getChronoUnitLabel(context: Context): String {
    return this?.bestChronoUnit()?.getLabelFromChronoUnit(context) ?: ""
}
//endregion

//region ChronoUnit Formatting
/**
 * Obtém um rótulo textual legível para uma unidade de tempo [ChronoUnit].
 * A string é obtida a partir dos recursos do Android (`strings.xml`).
 *
 * @receiver A [ChronoUnit] para a qual o rótulo é desejado.
 * @param context O [Context] da aplicação para acessar os recursos.
 * @return O rótulo localizado (ex: "Segundos", "Minutos", "Horas").
 * @throws IllegalArgumentException se a [ChronoUnit] não for suportada (SECONDS, MINUTES, HOURS).
 */
fun ChronoUnit.getLabelFromChronoUnit(context: Context): String {
    return when (this) {
        ChronoUnit.SECONDS -> context.getString(R.string.chrono_unit_seconds)
        ChronoUnit.MINUTES -> context.getString(R.string.chrono_unit_minutes)
        ChronoUnit.HOURS -> context.getString(R.string.chrono_unit_hours)
        else -> throw IllegalArgumentException(context.getString(R.string.invalid_label_chrono_unit_message))
    }
}