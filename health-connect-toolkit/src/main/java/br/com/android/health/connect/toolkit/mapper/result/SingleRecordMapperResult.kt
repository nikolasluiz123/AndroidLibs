package br.com.android.health.connect.toolkit.mapper.result

import br.com.android.room.toolkit.model.health.interfaces.IHealthConnectMetadata
import br.com.android.room.toolkit.model.health.interfaces.IRelationalHealthConnectEntity

/**
 * Um wrapper genérico para resultados de mapeamento que consistem em uma única
 * entidade de domínio, além dos metadados.
 *
 * É a implementação padrão de [IRecordMapperResult] para a maioria dos registros que não
 * possuem dados em série, como Passos, Distância ou Calorias.
 *
 * @param T O tipo da entidade de domínio principal, que deve implementar [IRelationalHealthConnectEntity].
 *
 * @property entity A instância da entidade de domínio mapeada.
 * @property metadata Os [HealthConnectMetadata] do registro original.
 *
 * @author Nikolas Luiz Schmitt
 */
data class SingleRecordMapperResult<T: IRelationalHealthConnectEntity>(
    val entity: T,
    override val metadata: IHealthConnectMetadata
) : IRecordMapperResult {

    override fun getEntityIdRelation(): List<String?> = listOf(entity.relationId)
}