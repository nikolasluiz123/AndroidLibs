package br.com.core.android.utils.media

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import br.com.core.android.utils.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

/**
 * Objeto utilitário para operações relacionadas a vídeos no Android.
 */
object VideoUtils {
    private const val VIDEOS_FOLDER_NAME = "videos"
    private const val DEFAULT_VIDEO_EXTENSION = "mp4"

    /**
     * Gera um nome de arquivo padrão para um novo vídeo, baseado no timestamp atual.
     *
     * @return Um nome de arquivo no formato `video_1672531200000.mp4`.
     */
    fun getDefaultVideoName(): String {
        return "video_${System.currentTimeMillis()}.$DEFAULT_VIDEO_EXTENSION"
    }

    /**
     * Cria um [File] vazio no diretório de vídeos específico do app.
     * Se o diretório não existir, ele será criado.
     *
     * @param context O [Context] da aplicação.
     * @param fileName O nome do arquivo a ser criado. Se for `null`, um nome padrão será gerado.
     * @return O objeto [File] criado.
     */
    fun createVideoFile(context: Context, fileName: String? = null): File {
        val videoDir = File(context.getExternalFilesDir(VIDEOS_FOLDER_NAME), "")
        if (!videoDir.exists()) videoDir.mkdirs()

        return File(videoDir, fileName ?: getDefaultVideoName())
    }

    /**
     * Gera um nome de arquivo para a versão comprimida de um vídeo.
     *
     * @param videoFileName O nome do arquivo de vídeo original.
     * @return O nome do arquivo com o sufixo `_compressed`.
     */
    fun getCompressedVideoFileName(videoFileName: String): String {
        val nameWithoutExtension = FileUtils.getFileNameWithoutExtension(videoFileName)
        return "${nameWithoutExtension}_compressed.$DEFAULT_VIDEO_EXTENSION"
    }

    /**
     * Cria uma cópia de um vídeo a partir de uma [Uri] no armazenamento interno do app.
     * Executa a operação de I/O em uma corrotina no [Dispatchers.IO].
     *
     * @param context O [Context] da aplicação.
     * @param uri A `content://` [Uri] do vídeo de origem.
     * @return O [File] do vídeo copiado ou `null` em caso de erro.
     */
    suspend fun createVideoFileFromUri(context: Context, uri: Uri): File? = withContext(Dispatchers.IO) {
        val fileName = FileUtils.getFileNameFromUri(context, uri)!!
        val videoFile = createVideoFile(context, fileName)

        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            FileOutputStream(videoFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        videoFile
    }

    /**
     * Gera uma thumbnail (miniatura) a partir de um arquivo de vídeo.
     * A operação é executada em uma corrotina no [Dispatchers.IO].
     *
     * @param filePath O caminho absoluto do arquivo de vídeo.
     * @param timeUs O timestamp (em microssegundos) do frame a ser extraído. Padrão: 1 segundo.
     * @return Um [Bitmap] da thumbnail ou `null` se o arquivo não existir ou a extração falhar.
     */
    suspend fun generateVideoThumbnail(filePath: String, timeUs: Long = 1_000_000): Bitmap? = withContext(Dispatchers.IO) {
        if (!FileUtils.getFileExists(filePath)) return@withContext null

        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(filePath)
            retriever.getFrameAtTime(timeUs, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        } finally {
            retriever.release()
        }
    }

    /**
     * Extrai a duração de um vídeo em segundos.
     *
     * @param file O [File] do vídeo.
     * @return A duração total em segundos (`Long`).
     */
    fun getVideoDurationInSeconds(file: File): Long {
        val retriever = MediaMetadataRetriever()
        return try {
            retriever.setDataSource(file.absolutePath)
            val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            val durationMs = durationStr?.toLongOrNull() ?: 0L
            durationMs / 1000
        } finally {
            retriever.release()
        }
    }

    /**
     * Extrai a resolução (largura e altura) de um vídeo.
     *
     * @param file O [File] do vídeo.
     * @return Um [Pair]<Int, Int> com (largura, altura) ou `null` se não for possível extrair.
     */
    fun getVideoResolution(file: File): Pair<Int, Int>? {
        val retriever = MediaMetadataRetriever()
        return try {
            retriever.setDataSource(file.absolutePath)
            val width = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)?.toIntOrNull()
            val height = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)?.toIntOrNull()

            if (width != null && height != null) Pair(width, height) else null
        } finally {
            retriever.release()
        }
    }

    /**
     * Deleta um arquivo de vídeo do armazenamento.
     *
     * @param context O [Context] da aplicação.
     * @param filePath O caminho absoluto do vídeo a ser deletado.
     * @throws FileNotFoundException se o arquivo não puder ser deletado.
     */
    fun deleteVideoFile(context: Context, filePath: String) {
        val successDeleteFile = FileUtils.deleteFile(filePath)
        if (!successDeleteFile) {
            throw FileNotFoundException(context.getString(R.string.video_file_not_found_message))
        }
    }
}