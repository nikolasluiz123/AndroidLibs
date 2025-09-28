package br.com.android.room.toolkit.model.interfaces

/**
 * Define a propriedade base para todas as entidades do modelo de domínio que possuem
 * um identificador único.
 *
 * @property id O identificador único da entidade.
 *
 * @author Nikolas Luiz Schmitt
 */
interface BaseModel {
    var id: String
}