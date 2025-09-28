package br.com.android.compose.charts.entries.legend

/**
 * Representa uma única entrada na legenda de um gráfico.
 *
 * @property label O texto descritivo para o item da legenda.
 *
 * @author Nikolas Luiz Schmitt
 */
data class LegendEntry(
    val label: String,
)