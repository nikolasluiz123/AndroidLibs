package br.com.android.room.toolkit.model.health.interfaces

/**
 * Define um contrato para entidades do Health Connect que possuem uma relação
 * com uma entidade principal do modelo de domínio do aplicativo.
 *
 * Permite vincular um registro de saúde (ex: uma medição de passos) a um registro
 * mais amplo (ex: um diário de atividade).
 *
 * @property relationId O `id` da entidade do modelo de domínio com a qual este registro
 * de saúde está relacionado.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IRelationalHealthConnectEntity {
    val relationId: String?
}