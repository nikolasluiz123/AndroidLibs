package br.com.android.health.connect.toolkit.mapper

import androidx.health.connect.client.records.Record
import br.com.android.health.connect.toolkit.mapper.result.IRecordMapperResult
import br.com.android.health.connect.toolkit.service.AbstractBaseHealthConnectService
import br.com.android.health.connect.toolkit.service.filter.RangeFilter
import br.com.android.room.toolkit.model.health.HealthConnectMetadata
import br.com.android.room.toolkit.model.health.interfaces.IHealthDataRangeEntity
import java.time.Instant

/**
 * Classe base para mappers que não apenas leem dados do Health Connect, mas também os
 * **associam** a entidades de domínio existentes no aplicativo.
 *
 * Este padrão é crucial para vincular dados de saúde (como passos, calorias, etc.)
 * a eventos específicos do aplicativo (ex: uma sessão de exercício). A associação
 * é tipicamente baseada na sobreposição de intervalos de tempo.
 *
 * @param RESULT O tipo de resultado esperado, que implementa [IRecordMapperResult].
 * @param RECORD O tipo de [Record] específico do Health Connect.
 * @param SERVICE O tipo de [AbstractBaseHealthConnectService] usado para buscar os registros.
 *
 * @property service A instância do serviço responsável por ler os dados.
 *
 * @see [IHealthDataRangeEntity]
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractHealthDataAssociatingMapper<RESULT : IRecordMapperResult, RECORD : Record, SERVICE : AbstractBaseHealthConnectService<RECORD>>(
    protected val service: SERVICE
) : AbstractHealthConnectBaseMapper() {

    /**
     * Executa a leitura dos dados do Health Connect e, em seguida, mapeia e associa
     * cada registro com as entidades de domínio fornecidas.
     *
     * @param T O tipo da entidade de associação, que deve implementar [IHealthDataRangeEntity].
     * @param filter O filtro de [RangeFilter] para a consulta.
     * @param associationEntities A lista de entidades (ex: sessões de exercício)
     * com as quais os registros de saúde tentarão se associar.
     * @return Uma lista de [RESULT] contendo os dados mapeados e associados.
     */
    suspend fun <T : IHealthDataRangeEntity> mapAndAssociate(
        filter: RangeFilter,
        associationEntities: List<T>
    ): List<RESULT> {
        val records = service.read(filter)

        return records.mapNotNull { record ->
            val metadata = getMetadataFrom(record)
            continueMappingAndAssociate(record, metadata, associationEntities)
        }
    }

    /**
     * Onde a lógica de mapeamento e associação específica da classe filha deve ser implementada.
     *
     * @param ENTITY O tipo da entidade de associação.
     * @param record O [Record] individual retornado da consulta.
     * @param metadata Os [HealthConnectMetadata] extraídos do [record].
     * @param associationEntities A lista completa de entidades de associação para busca.
     * @return O objeto [RESULT] mapeado e associado, ou `null` se a associação não for possível.
     */
    protected abstract suspend fun <ENTITY : IHealthDataRangeEntity> continueMappingAndAssociate(
        record: RECORD,
        metadata: HealthConnectMetadata,
        associationEntities: List<ENTITY>
    ): RESULT?

    /**
     * Utilitário para encontrar uma entidade de associação que se sobrepõe
     * temporalmente a um registro do Health Connect.
     *
     * A lógica de sobreposição verifica se:
     * `(Início do Registro <= Fim da Entidade) E (Fim do Registro >= Início da Entidade)`
     *
     * @param T O tipo da entidade de associação.
     * @param recordStartTime A data/hora de início do registro.
     * @param recordEndTime A data/hora de fim do registro.
     * @param entities A lista de entidades para verificar a correspondência.
     * @return A primeira entidade [T] que sobrepõe o intervalo de tempo do registro,
     * ou `null` se nenhuma correspondência for encontrada.
     */
    protected fun <T : IHealthDataRangeEntity> findMatchingEntity(
        recordStartTime: Instant?,
        recordEndTime: Instant?,
        entities: List<T>
    ): T? {
        if (recordStartTime == null || recordEndTime == null) return null

        return entities.firstOrNull { entity ->
            val entityStart = entity.rangeStartTime
            val entityEnd = entity.rangeEndTime ?: return@firstOrNull false

            (recordStartTime.isBefore(entityEnd) || recordStartTime == entityEnd) &&
                    (recordEndTime.isAfter(entityStart) || recordEndTime == entityStart)
        }
    }
}