package br.com.android.ui.compose.components.bottomsheet.interfaces

/**
 * Define o contrato para enums que representam as opções em um [BottomSheet].
 * Esta interface garante que cada opção de enum tenha um índice, que pode ser útil para ordenação ou identificação.
 *
 * @property index O índice do item na lista de opções.
 *
 * @see IBottomSheetItem
 * @author Nikolas Luiz Schmitt
 */
interface IEnumOptionsBottomSheet {
    val index: Int
}