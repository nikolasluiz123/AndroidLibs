package br.com.android.ui.compose.components.fields.validation

import br.com.android.ui.compose.components.fields.validation.interfaces.IEnumFieldValidation

/**
 * Implementação de [ValidationError] que permite especificar o campo a qual a validação se refere.
 *
 * O [field] é opcional pois existem casos de uso que fazemos validações de campos específicos e
 * validações 'globais' ao mesmo tempo, possibilitando esse campo ser ``null`` o retorno do caso de uso
 * pode ser o mesmo.
 *
 * @param field Campo a qual a validação se refere. Opcional para cenários onde não há um campo específico.
 */
open class FieldValidationError<FIELD>(
    val field: Enum<FIELD>?,
    message: String,
) : ValidationError(message) where FIELD : Enum<FIELD>, FIELD : IEnumFieldValidation

