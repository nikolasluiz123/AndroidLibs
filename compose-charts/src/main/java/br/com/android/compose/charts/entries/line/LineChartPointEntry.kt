package br.com.android.compose.charts.entries.line

/**
 * Representa um ponto de dados no eixo X para um [br.com.android.compose.charts.composables.line.LineChart].
 *
 * Cada instância pode conter múltiplos valores, permitindo o desenho de várias linhas
 * simultaneamente no gráfico.
 *
 * @property label O rótulo a ser exibido no eixo X para este ponto.
 * @property values Uma lista de valores `Float`. Cada valor na lista corresponde a um ponto em uma
 * linha diferente do gráfico, na mesma posição do eixo X.
 *
 * @author Nikolas Luiz Schmitt
 */
data class LineChartPointEntry(
    val label: String,
    val values: List<Float>
)