package br.com.android.ui.compose.components.tabs.state.interfaces

/**
 * Define um contrato para Enums que representam abas (Tabs).
 *
 * Esta interface padroniza a forma como as abas são criadas e identificadas, garantindo que
 * cada aba tenha um índice numérico e um ID de recurso de string para seu rótulo.
 *
 * Deve ser implementada por um `enum class` onde cada entrada representa uma aba.
 *
 * @property index O índice da aba, geralmente correspondendo à sua posição.
 * @property labelResId O ID do recurso de string (`R.string.*`) para o título da aba.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IEnumTab {
    val index: Int
    val labelResId: Int
}