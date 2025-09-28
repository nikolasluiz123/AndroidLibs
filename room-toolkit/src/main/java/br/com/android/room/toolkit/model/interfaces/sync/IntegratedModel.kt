package br.com.android.room.toolkit.model.interfaces.sync

import br.com.android.room.toolkit.model.enums.EnumTransmissionState
import br.com.android.room.toolkit.model.interfaces.BaseModel

/**
 * Define um contrato para entidades que fazem parte de um sistema de integração de dados,
 * necessitando de um controle de estado de transmissão.
 *
 * Herda de [br.com.android.room.toolkit.model.interfaces.BaseModel], garantindo que toda entidade integrada tenha um `id`.
 *
 * @property transmissionState O estado atual de sincronização da entidade com um sistema externo.
 *
 * @see [br.com.android.room.toolkit.model.enums.EnumTransmissionState]
 * @author Nikolas Luiz Schmitt
 */
interface IntegratedModel: BaseModel {
    var transmissionState: EnumTransmissionState
}