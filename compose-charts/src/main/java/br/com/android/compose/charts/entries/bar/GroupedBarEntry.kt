package br.com.android.compose.charts.entries.bar

/**
 * Representa uma única entrada de dados para um [br.com.android.compose.charts.composables.bar.grouped.GroupedBarChart].
 *
 * Cada instância corresponde a um grupo de barras no eixo X.
 *
 * @property label O rótulo exibido no eixo X para este grupo de barras.
 * @property values Uma lista de valores `Float`, onde cada valor corresponde a uma barra
 * individual dentro do grupo.
 *
 * @author Nikolas Luiz Schmitt
 */
data class GroupedBarEntry(
    override val label: String,
    val values: List<Float>
) : BarChartEntry