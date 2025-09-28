package br.com.android.ui.compose.components.states

import br.com.android.ui.compose.components.dialog.message.MessageDialogState

/**
 * Define um contrato para classes de estado (State) que precisam lidar com erros e exceções,
 * permitindo que eles sejam exibidos ao usuário através de um diálogo de mensagem.
 *
 * Ao implementar esta interface, uma classe de estado sinaliza que ela pode conter informações
 * sobre um erro que ocorreu e fornece o estado necessário para exibir um
 * [MessageDialog].
 *
 * @property messageDialogState O estado que contém as informações do erro (título, mensagem)
 * a serem exibidas no diálogo.
 *
 * @see [MessageDialogState]
 * @author Nikolas Luiz Schmitt
 */
interface IThrowableUIState {
    val messageDialogState: MessageDialogState
}