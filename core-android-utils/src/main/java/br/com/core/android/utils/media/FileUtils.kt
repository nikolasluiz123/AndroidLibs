package br.com.core.android.utils.media

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.content.FileProvider
import java.io.File

/**
 * Objeto utilitário para operações comuns relacionadas a arquivos e Uris no Android.
 */
object FileUtils {

    /**
     * Gera a string de autoridade para o [FileProvider] com base no `packageName` da aplicação.
     *
     * @param context O [Context] da aplicação.
     * @return A string de autoridade, no formato `br.com.pacote.app.fileprovider`.
     */
    fun getFileProviderAuthority(context: Context): String {
        return "${context.packageName}.fileprovider"
    }

    /**
     * Gera uma `content://` [Uri] para um arquivo específico usando o [FileProvider] da aplicação.
     * Essencial para compartilhar arquivos com outros apps de forma segura a partir do Android N (API 24).
     *
     * @param context O [Context] da aplicação.
     * @param file O [File] para o qual a Uri será gerada.
     * @return A [Uri] segura para o arquivo.
     */
    fun getUriForFileUsingProvider(context: Context, file: File): Uri {
        val authority = getFileProviderAuthority(context)
        return FileProvider.getUriForFile(context, authority, file)
    }

    /**
     * Extrai o nome do arquivo com sua extensão a partir de um caminho completo.
     *
     * Exemplo: `/path/to/my_video.mp4` -> `my_video.mp4`
     *
     * @param filePath O caminho completo do arquivo.
     * @return O nome do arquivo com a extensão.
     */
    fun getFileNameWithExtensionFromFilePath(filePath: String): String {
        return filePath.substringAfterLast("/")
    }

    /**
     * Extrai o nome do arquivo sem a extensão.
     *
     * Exemplo: `my_video.mp4` -> `my_video`
     *
     * @param fileName O nome do arquivo com extensão.
     * @return O nome do arquivo sem a extensão.
     */
    fun getFileNameWithoutExtension(fileName: String): String {
        return fileName.substringBeforeLast(".")
    }

    /**
     * Obtém o nome de exibição de um arquivo a partir de sua `content://` [Uri].
     *
     * @param context O [Context] da aplicação.
     * @param uri A [Uri] do arquivo.
     * @return O nome do arquivo ou `null` se não puder ser resolvido.
     */
    fun getFileNameFromUri(context: Context, uri: Uri): String? {
        var name: String? = null
        val returnCursor = context.contentResolver.query(uri, null, null, null, null)

        returnCursor?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex >= 0 && cursor.moveToFirst()) {
                name = cursor.getString(nameIndex)
            }
        }
        return name
    }

    /**
     * Calcula o tamanho de um arquivo em Kilobytes (KB).
     *
     * @param file O [File] a ser medido.
     * @return O tamanho do arquivo em KB (`Long`).
     */
    fun getFileSizeInKB(file: File): Long {
        return file.length() / 1024
    }

    /**
     * Verifica de forma simples se um arquivo existe no caminho especificado.
     *
     * @param filePath O caminho completo do arquivo.
     * @return `true` se o arquivo existir, `false` caso contrário.
     */
    fun getFileExists(filePath: String): Boolean {
        return File(filePath).exists()
    }

    /**
     * Deleta um arquivo do armazenamento.
     *
     * @param filePath O caminho completo do arquivo a ser deletado.
     * @return `true` se o arquivo foi deletado com sucesso, `false` caso contrário.
     */
    fun deleteFile(filePath: String): Boolean {
        val file = File(filePath)
        return file.delete()
    }

    /**
     * Converte uma lista de caminhos ([String]) em uma lista de objetos [File],
     * incluindo apenas os arquivos que realmente existem.
     *
     * @param paths A lista de caminhos de arquivo.
     * @return Uma lista de [File] contendo apenas os arquivos existentes.
     */
    fun getFileListFromPaths(paths: List<String>): List<File> {
        return paths.mapNotNull { path ->
            val file = File(path)
            if (file.exists()) file else null
        }
    }

    /**
     * Deleta uma lista de arquivos do armazenamento.
     *
     * @param files A lista de [File] a ser deletada.
     */
    fun deleteFiles(files: List<File>) {
        files.forEach { file ->
            if (file.exists()) {
                file.delete()
            }
        }
    }
}