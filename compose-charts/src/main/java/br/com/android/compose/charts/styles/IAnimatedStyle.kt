package br.com.android.compose.charts.styles

/**
 * Define um contrato para estilos que suportam animação.
 *
 * Esta interface padroniza a configuração de duração e atraso para animações
 * em componentes de gráfico.
 *
 * @property animationDuration A duração da animação em milissegundos.
 * @property animationDelay O atraso antes do início da animação em milissegundos.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IAnimatedStyle {
    val animationDuration: Int
    val animationDelay: Long
}