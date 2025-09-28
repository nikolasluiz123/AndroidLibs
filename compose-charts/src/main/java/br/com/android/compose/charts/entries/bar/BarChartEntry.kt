package br.com.android.compose.charts.entries.bar

/**
 * Interface de marcação para representar uma entrada de dados em um gráfico de barras.
 *
 * Define a propriedade comum a todas as entradas de barra: um rótulo de texto.
 *
 * @property label O rótulo a ser exibido no eixo X para esta entrada.
 *
 * @author Nikolas Luiz Schmitt
 */
interface BarChartEntry {
    val label: String
}