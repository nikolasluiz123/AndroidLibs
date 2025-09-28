package br.com.android.compose.charts.entries.bar

/**
 * Representa uma única entrada de dados para um [br.com.android.compose.charts.composables.bar.simple.SimpleBarChart].
 *
 * Cada instância corresponde a uma única barra no gráfico.
 *
 * @property label O rótulo exibido no eixo X para esta barra.
 * @property value O valor numérico que determina a altura da barra.
 *
 * @author Nikolas Luiz Schmitt
 */
data class SimpleBarEntry(
    override val label: String,
    val value: Float
) : BarChartEntry