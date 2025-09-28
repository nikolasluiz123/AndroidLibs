package br.com.android.health.connect.toolkit.mapper

import androidx.health.connect.client.records.Record
import br.com.android.health.connect.toolkit.mapper.result.IRecordMapperResult
import br.com.android.health.connect.toolkit.service.AbstractBaseHealthConnectService
import br.com.android.health.connect.toolkit.service.filter.RangeFilter
import br.com.android.room.toolkit.model.health.HealthConnectMetadata

/**
 * Classe base abstrata para mappers que leem e transformam dados do Health Connect.
 *
 * Este padrão orquestra o fluxo de:
 * 1.  Ler registros ([Record]) de um [AbstractBaseHealthConnectService].
 * 2.  Extrair os metadados comuns usando [AbstractHealthConnectBaseMapper].
 * 3.  Delegar a lógica de mapeamento do conteúdo específico do registro para o método
 * abstrato [continueMapping].
 *
 * @param RESULT O tipo de resultado do mapeamento, que deve implementar [IRecordMapperResult].
 * @param RECORD O tipo de [Record] específico do Health Connect que este mapper processa (ex: `StepsRecord`).
 * @param SERVICE O tipo de [AbstractBaseHealthConnectService] usado para buscar os registros.
 *
 * @property service A instância do serviço responsável por ler os dados do Health Connect.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractHealthConnectRecordMapper<RESULT : IRecordMapperResult, RECORD : Record, SERVICE : AbstractBaseHealthConnectService<RECORD>>(
    protected val service: SERVICE
) : AbstractHealthConnectBaseMapper() {

    /**
     * Executa a leitura dos dados do Health Connect usando o [service] e o [filter] fornecido,
     * e então mapeia cada registro para o tipo [RESULT].
     *
     * @param filter O filtro de [RangeFilter] (data/hora inicial e final) para a consulta.
     * @return Uma lista de [RESULT] contendo os dados mapeados.
     */
    suspend fun map(filter: RangeFilter): List<RESULT> {
        val records = service.read(filter)

        return records.map { record ->
            val metadata = getMetadataFrom(record)
            continueMapping(record, metadata)
        }
    }

    /**
     * Onde a lógica de mapeamento específica da classe filha deve ser implementada.
     *
     * Este método é chamado para cada registro após os metadados ([HealthConnectMetadata])
     * já terem sido extraídos.
     *
     * @param record O [Record] individual retornado da consulta.
     * @param metadata Os [HealthConnectMetadata] extraídos do [record].
     * @return O objeto [RESULT] mapeado.
     */
    protected abstract suspend fun continueMapping(
        record: RECORD,
        metadata: HealthConnectMetadata
    ): RESULT
}