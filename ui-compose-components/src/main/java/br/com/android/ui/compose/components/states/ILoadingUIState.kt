package br.com.android.ui.compose.components.states

/**
 * Define um contrato para classes de estado (State) que gerenciam a exibição de um indicador de carregamento.
 *
 * Esta interface padroniza a forma como os componentes de UI podem reagir a estados de loading,
 * permitindo que ViewModels e Composables interajam com o estado de carregamento de forma desacoplada.
 *
 * Implemente esta interface em classes que precisam controlar a visibilidade de componentes
 * como [BaseLinearProgressIndicator] ou [BaseCircularBlockUIProgressIndicator].
 *
 * @property showLoading Um valor booleano que indica se o indicador de carregamento deve ser exibido.
 * @property onToggleLoading Uma função para alternar o valor de [showLoading].
 *
 * @author Nikolas Luiz Schmitt
 */
interface ILoadingUIState {
    val showLoading: Boolean
    val onToggleLoading: () -> Unit
}