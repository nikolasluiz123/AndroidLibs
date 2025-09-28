package br.com.android.health.connect.toolkit.service

import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.Record
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import br.com.android.health.connect.toolkit.service.filter.RangeFilter
import kotlin.reflect.KClass

/**
 * Classe base abstrata para serviços que interagem com o [HealthConnectClient].
 *
 * Padroniza a leitura de um tipo específico de [Record] (definido por [R]),
 * abstraindo a criação da requisição e a chamada ao cliente. As classes filhas
 * precisam apenas especificar o [KClass] do [Record] que manipulam.
 *
 * @param R O tipo de [Record] que este serviço irá ler (ex: `StepsRecord`, `HeartRateRecord`).
 *
 * @property client A instância do [HealthConnectClient] fornecida via injeção de dependência.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractBaseHealthConnectService<R: Record>(
    protected val client: HealthConnectClient
) {
    /**
     * Deve ser implementado pelas classes filhas para especificar o [KClass] do [Record]
     * que elas manipulam.
     *
     * Exemplo: `override fun getRecordType() = StepsRecord::class`
     *
     * @return O [KClass] do [Record].
     */
    protected abstract fun getRecordType(): KClass<R>

    /**
     * Lê os registros do Health Connect com base em um [RangeFilter].
     *
     * @param filter O [RangeFilter] contendo o período de início e fim da consulta.
     * @return Uma lista de registros do tipo [R].
     */
    suspend fun read(filter: RangeFilter): List<R> {
        val request = createDefaultReadRecordsRequest(filter)
        return client.readRecords(request).records
    }

    /**
     * Cria a requisição padrão [ReadRecordsRequest] para a leitura de dados.
     *
     * Utiliza o [TimeRangeFilter.between] com base no [filter] fornecido.
     * Este método pode ser sobrescrito para adicionar outras configurações à requisição.
     *
     * @param filter O [RangeFilter] a ser usado.
     * @return A instância de [ReadRecordsRequest] configurada.
     */
    open fun createDefaultReadRecordsRequest(filter: RangeFilter): ReadRecordsRequest<R> {
        return ReadRecordsRequest(
            recordType = getRecordType(),
            timeRangeFilter = TimeRangeFilter.between(filter.start, filter.end)
        )
    }
}