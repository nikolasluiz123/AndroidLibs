package br.com.android.room.toolkit.model.health.interfaces

import java.time.Instant

/**
 * Define um contrato para entidades de dados de saúde que representam um evento ou
 * medição ocorrida ao longo de um intervalo de tempo.
 *
 * @property rangeStartTime O [Instant] de início do intervalo.
 * @property rangeEndTime O [Instant] de fim do intervalo, que pode ser nulo para eventos pontuais.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IHealthDataRangeEntity : IHealthDataCollected {
    val rangeStartTime: Instant
    val rangeEndTime: Instant?
}