package br.com.android.ui.compose.components.bottomsheet.interfaces

/**
 * Define a estrutura padrão para um item a ser exibido em um [BottomSheet].
 * Qualquer classe que represente um item de um bottom sheet deve implementar esta interface
 * para garantir que todos os dados necessários para a renderização do item estejam disponíveis.
 *
 * @param T O tipo do enum que representa a opção do bottom sheet, que deve implementar [IEnumOptionsBottomSheet].
 *
 * @property option O enum que representa a opção específica do item.
 * @property iconResId O ID do recurso do drawable para o ícone do item. Nulo se nenhum ícone for necessário.
 * @property labelResId O ID do recurso da string para o rótulo do item.
 * @property iconDescriptionResId O ID do recurso da string para a descrição do conteúdo do ícone, para fins de acessibilidade. Nulo se nenhum ícone for usado.
 *
 * @see BottomSheet
 * @see IEnumOptionsBottomSheet
 * @author Nikolas Luiz Schmitt
 */
interface IBottomSheetItem<T: IEnumOptionsBottomSheet> {
    val option: T
    val iconResId: Int?
    val labelResId: Int
    val iconDescriptionResId: Int?
}