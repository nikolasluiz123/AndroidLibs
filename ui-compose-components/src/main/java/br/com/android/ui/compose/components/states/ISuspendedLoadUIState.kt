package br.com.android.ui.compose.components.states

/**
 * Define um contrato para classes de estado (State) que precisam controlar a execução de operações de
 * carregamento assíncronas (suspensas).
 *
 * Esta interface é útil para cenários onde uma operação de carregamento (como uma chamada de rede)
 * só deve ser executada uma vez ou sob condições específicas, como a primeira vez que um Composable entra
 * na composição.
 *
 * @property executeLoad Uma flag que o Composable pode observar. Quando `true`, a operação de
 * carregamento deve ser disparada. O responsável por executar a carga (ex: um `LaunchedEffect`)
 * deve então setar este valor para `false` para evitar re-execuções desnecessárias.
 *
 * @author Nikolas Luiz Schmitt
 */
interface ISuspendedLoadUIState {
    var executeLoad: Boolean
}