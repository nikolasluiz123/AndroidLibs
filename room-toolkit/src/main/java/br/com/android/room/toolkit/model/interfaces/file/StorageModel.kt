package br.com.android.room.toolkit.model.interfaces.file

import br.com.android.room.toolkit.model.enums.EnumDownloadState
import br.com.android.room.toolkit.model.enums.EnumTransmissionState
import java.time.LocalDateTime

/**
 * Define um contrato para entidades que representam arquivos gerenciados em um
 * serviço de armazenamento em nuvem.
 *
 * Esta interface centraliza as propriedades necessárias para controlar o upload,
 * download e o estado de transmissão desses arquivos.
 *
 * @property storageTransmissionDate A data e hora da última tentativa de transmissão (upload).
 * @property storageTransmissionState O estado atual do upload do arquivo.
 * @property storageDownloadState O estado atual do download do arquivo.
 * @property storageUrl A URL do arquivo no serviço de armazenamento.
 *
 * @author Nikolas Luiz Schmitt
 */
interface StorageModel {
    var storageTransmissionDate: LocalDateTime?
    var storageTransmissionState: EnumTransmissionState
    var storageDownloadState: EnumDownloadState
    var storageUrl: String?
}