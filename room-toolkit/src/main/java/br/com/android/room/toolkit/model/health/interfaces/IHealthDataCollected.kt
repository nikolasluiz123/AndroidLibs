package br.com.android.room.toolkit.model.health.interfaces

import br.com.android.room.toolkit.model.interfaces.sync.IntegratedModel

/**
 * Define um contrato para entidades que precisam rastrear se os dados de saúde associados
 * a elas já foram coletados (ex: do Health Connect).
 *
 * Herda de [IntegratedModel], incorporando também o controle de estado de transmissão.
 *
 * @property healthDataCollected Flag que indica se os dados de saúde foram coletados.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IHealthDataCollected : IntegratedModel {
    var healthDataCollected: Boolean
}