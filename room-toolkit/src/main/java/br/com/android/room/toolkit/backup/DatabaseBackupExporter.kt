package br.com.android.room.toolkit.backup

import android.content.Context
import br.com.android.room.toolkit.R
import br.com.core.utils.enums.EnumDateTimePatterns
import br.com.core.utils.extensions.dateTimeNow
import br.com.core.utils.extensions.format
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.time.ZoneId

/**
 * Classe utilitária para exportar o banco de dados do Room para um arquivo de backup.
 *
 * O arquivo de backup é salvo em um diretório específico da aplicação (`backups`)
 * e nomeado com um timestamp para garantir a unicidade.
 *
 * @param context O [Context] da aplicação, necessário para acessar os caminhos dos arquivos.
 *
 * @author Nikolas Luiz Schmitt
 */
open class DatabaseBackupExporter(private val context: Context) {

    /**
     * Exporta o arquivo do banco de dados especificado para um novo arquivo de backup.
     *
     * @param dbFileNameWithExtension O nome do arquivo do banco de dados (ex: "app.db").
     * @return O caminho absoluto para o arquivo de backup criado.
     * @throws IllegalStateException se o arquivo do banco de dados original não for encontrado.
     */
    suspend fun export(dbFileNameWithExtension: String): String = withContext(Dispatchers.IO) {
        val dbFile = context.getDatabasePath(dbFileNameWithExtension)
        verifyDatabaseFileExists(dbFile)

        val backupFile = createNewBackupFile()
        writeDatabaseInBackupFile(dbFile, backupFile)

        backupFile.absolutePath
    }

    private fun writeDatabaseInBackupFile(dbFile: File, backupFile: File) {
        Files.copy(dbFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
    }

    private fun createNewBackupFile(): File {
        val backupDir = getBackupFileDir()
        val timestamp = dateTimeNow(ZoneId.systemDefault()).format(EnumDateTimePatterns.BACKUP_DB_FILE_NAME)

        return File(backupDir, "$timestamp.db")
    }

    private fun getBackupFileDir(): File {
        return File(context.getExternalFilesDir(null), BACKUPS_DIR_NAME).apply {
            if (!exists()) mkdirs()
        }
    }

    private fun verifyDatabaseFileExists(dbFile: File) {
        if (!dbFile.exists()) {
            val message = context.getString(
                R.string.database_backup_exporter_file_not_found_message,
                dbFile.absolutePath
            )

            throw IllegalStateException(message)
        }
    }

    companion object {
        private const val BACKUPS_DIR_NAME = "backups"
    }
}