package br.com.android.ui.compose.components.fields.validation

/**
 * Classe base para erros de validação de campos.
 *
 * @property message Mensagem de erro a ser exibida.
 */
open class ValidationError(
    val message: String,
)