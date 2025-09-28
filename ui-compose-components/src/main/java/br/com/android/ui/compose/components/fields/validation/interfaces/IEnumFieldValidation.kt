package br.com.android.ui.compose.components.fields.validation.interfaces

/**
 * Interface utilizada por Enums para validar campos de telas.
 *
 * @property labelResId Referência para o resource ID do label do campo.
 * @property maxLength Tamanho máximo do campo. Normalmente utilzado em campos de texto.
 */
interface IEnumFieldValidation {
    val labelResId: Int
    val maxLength: Int
}