package br.com.android.health.connect.toolkit.mapper.result

import br.com.android.room.toolkit.model.health.HealthConnectMetadata


/**
 * Define o contrato base para todos os objetos de resultado gerados pelos mappers do Health Connect.
 *
 * Garante que todo resultado contenha os metadados do registro original e um método para
 * obter os IDs das entidades de domínio relacionadas.
 *
 * @property metadata Os [HealthConnectMetadata] associados ao registro mapeado.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IRecordMapperResult {
    val metadata: HealthConnectMetadata

    /**
     * Retorna uma lista de IDs das entidades de domínio associadas a este resultado de mapeamento.
     *
     * Pode haver mais de um ID em cenários complexos, mas geralmente haverá apenas um.
     *
     * @return A lista de IDs de relação.
     */
    fun getEntityIdRelation(): List<String?>
}