package br.com.android.room.toolkit.dao

import androidx.room.Update
import br.com.android.room.toolkit.model.enums.EnumTransmissionState
import br.com.android.room.toolkit.model.interfaces.sync.IntegratedModel

/**
 * Um DAO de manutenção abstrato especializado para entidades que implementam [IntegratedModel].
 *
 * Estende [MaintenanceDAO] e adiciona a lógica para, opcionalmente, marcar o
 * [EnumTransmissionState] da entidade como [EnumTransmissionState.PENDING]
 * durante as operações de atualização. Isso é útil para rastrear registros que
 * foram modificados e precisam ser sincronizados com um servidor.
 *
 * @param T O tipo da entidade, que deve ser uma subclasse de [IntegratedModel].
 *
 * @see [IntegratedModel]
 * @see [EnumTransmissionState]
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class IntegratedMaintenanceDAO<T: IntegratedModel>: MaintenanceDAO<T>() {

    /**
     * Atualiza um modelo. Pode opcionalmente marcar o estado de transmissão como PENDING.
     *
     * @param model O modelo a ser atualizado.
     * @param writeTransmissionState Se `true`, define `transmissionState` como [EnumTransmissionState.PENDING].
     */
    open suspend fun update(model: T, writeTransmissionState: Boolean = false) {
        if (writeTransmissionState) {
            model.transmissionState = EnumTransmissionState.PENDING
        }

        internalUpdate(model)
    }

    /**
     * Atualiza uma lista de modelos em lote. Pode opcionalmente marcar o estado de transmissão de todos
     * como PENDING.
     *
     * @param models A lista de modelos a ser atualizada.
     * @param writeTransmissionState Se `true`, define `transmissionState` de cada modelo como [EnumTransmissionState.PENDING].
     */
    open suspend fun updateBatch(models: List<T>, writeTransmissionState: Boolean = false) {
        if (writeTransmissionState) {
            models.forEach {
                it.transmissionState = EnumTransmissionState.PENDING
            }
        }

        internalUpdateBatch(models)
    }

    @Update
    protected abstract suspend fun internalUpdate(model: T)

    @Update
    protected abstract suspend fun internalUpdateBatch(models: List<T>)
}