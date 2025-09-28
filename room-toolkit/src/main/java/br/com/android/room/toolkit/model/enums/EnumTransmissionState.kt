package br.com.android.room.toolkit.model.enums

/**
 * Enum que representa os possíveis estados de transmissão de um registro para um servidor.
 *
 * É utilizado para controlar o fluxo de sincronização de dados.
 *
 * @author Nikolas Luiz Schmitt
 */
enum class EnumTransmissionState {
    PENDING, RUNNING, TRANSMITTED
}