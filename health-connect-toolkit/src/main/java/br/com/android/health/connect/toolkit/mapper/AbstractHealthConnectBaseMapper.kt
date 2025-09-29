package br.com.android.health.connect.toolkit.mapper

import androidx.health.connect.client.records.Record
import br.com.android.room.toolkit.model.health.enums.EnumDeviceType
import br.com.android.room.toolkit.model.health.enums.EnumRecordingMethod
import br.com.android.room.toolkit.model.health.interfaces.IHealthConnectMetadata
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * Classe base fundamental para todos os Mappers do Health Connect.
 *
 * Fornece funcionalidades utilitárias compartilhadas, principalmente a extração
 * e conversão dos metadados padrão de um [Record] do Health Connect para o
 * modelo de domínio [HealthConnectMetadata].
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractHealthConnectBaseMapper {

    /**
     * Extrai os metadados de um [Record] do Health Connect e os converte
     * para a entidade de domínio [HealthConnectMetadata].
     *
     * @param record O registro do Health Connect do qual os metadados serão extraídos.
     * @return A entidade [HealthConnectMetadata] preenchida.
     */
    abstract fun getMetadataFrom(record: Record): IHealthConnectMetadata
}