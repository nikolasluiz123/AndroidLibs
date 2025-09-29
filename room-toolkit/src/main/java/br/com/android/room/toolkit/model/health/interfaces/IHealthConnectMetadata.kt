package br.com.android.room.toolkit.model.health.interfaces

import br.com.android.room.toolkit.model.health.enums.EnumDeviceType
import br.com.android.room.toolkit.model.health.enums.EnumRecordingMethod
import br.com.android.room.toolkit.model.interfaces.sync.IntegratedModel
import java.time.LocalDateTime

/**
 * Interface para representar a tabela quee armazenará as informações comuns a todos os registros de
 * saúde do Health Connect.
 *
 * @param id O id (String) vindo de record.metadata.id. É o ID global único do registro no Health Connect.
 * @param transmissionState O estado de transmissão do registro que é enviado ao servidor.
 * @param dataOriginPackage O packageName do app que escreveu o dado (ex: "com.samsung.android.health").
 * @param lastModifiedTime A data/hora da última modificação feita pelo wearable ou pelo aplicativo.
 * @param clientRecordId O ID opcional que o app de origem usa.
 * @param deviceManufacturer O fabricante do dispositivo que gerou o dado (ex: "Samsung").
 * @param deviceModel O modelo do dispositivo (ex: "Galaxy Watch 6").
 * @param recordingMethod A forma de gravação.
 * @param deviceType O tipo de dispositivo.
 */
interface IHealthConnectMetadata: IntegratedModel {
    var dataOriginPackage: String?
    var lastModifiedTime: LocalDateTime?
    var clientRecordId: String?
    var deviceManufacturer: String?
    var deviceModel: String?
    var recordingMethod: EnumRecordingMethod?
    var deviceType: EnumDeviceType?
}