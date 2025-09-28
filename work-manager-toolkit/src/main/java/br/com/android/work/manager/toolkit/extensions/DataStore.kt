package br.com.android.work.manager.toolkit.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val WORKER_DATASTORE_NAME = "br_com_android_work_manager_toolkit_preferences"

/**
 * Delegação para criar uma instância singleton do [DataStore] para as preferências dos Workers.
 * O nome do arquivo de preferências será `br_com_android_work_manager_toolkit_preferences`.
 *
 * @author Nikolas Luiz Schmitt
 */
val Context.workerDataStore: DataStore<Preferences> by preferencesDataStore(name = WORKER_DATASTORE_NAME)

/**
 * Objeto que centraliza as chaves ([Preferences.Key]) usadas no [workerDataStore].
 *
 * A utilização deste objeto evita a duplicação de strings e erros de digitação,
 * mantendo as chaves consistentes em todo o aplicativo.
 *
 * @author Nikolas Luiz Schmitt
 */
object WorkerPreferencesKey {
    val RUN_EXPORT_WORKER = booleanPreferencesKey("runExportWorker")
    val RUN_IMPORT_WORKER = booleanPreferencesKey("runImportWorker")
}

/**
 * Lê o valor da flag `RUN_EXPORT_WORKER` do DataStore.
 *
 * Esta flag é utilizada para controlar programaticamente se os workers de exportação
 * devem ou não executar sua lógica.
 *
 * @receiver O [DataStore] de preferências do worker.
 * @return `true` se o worker de exportação deve ser executado, `false` caso contrário. Retorna `true` como valor padrão.
 *
 * @author Nikolas Luiz Schmitt
 */
suspend fun DataStore<Preferences>.getRunExportWorker(): Boolean {
    return this.data.map { preferences ->
        preferences[WorkerPreferencesKey.RUN_EXPORT_WORKER] ?: true
    }.first()
}

/**
 * Define o valor da flag `RUN_EXPORT_WORKER` no DataStore.
 *
 * @receiver O [DataStore] de preferências do worker.
 * @param value O novo valor booleano a ser salvo.
 *
 * @author Nikolas Luiz Schmitt
 */
suspend fun DataStore<Preferences>.setRunExportWorker(value: Boolean) {
    this.edit { preferences ->
        preferences[WorkerPreferencesKey.RUN_EXPORT_WORKER] = value
    }
}

/**
 * Lê o valor da flag `RUN_IMPORT_WORKER` do DataStore.
 *
 * Esta flag é utilizada para controlar programaticamente se os workers de importação
 * devem ou não executar sua lógica.
 *
 * @receiver O [DataStore] de preferências do worker.
 * @return `true` se o worker de importação deve ser executado, `false` caso contrário. Retorna `true` como valor padrão.
 *
 * @author Nikolas Luiz Schmitt
 */
suspend fun DataStore<Preferences>.getRunImportWorker(): Boolean {
    return this.data.map { preferences ->
        preferences[WorkerPreferencesKey.RUN_IMPORT_WORKER] ?: true
    }.first()
}

/**
 * Define o valor da flag `RUN_IMPORT_WORKER` no DataStore.
 *
 * @receiver O [DataStore] de preferências do worker.
 * @param value O novo valor booleano a ser salvo.
 *
 * @author Nikolas Luiz Schmitt
 */
suspend fun DataStore<Preferences>.setRunImportWorker(value: Boolean) {
    this.edit { preferences ->
        preferences[WorkerPreferencesKey.RUN_IMPORT_WORKER] = value
    }
}