package br.com.core.android.utils.media.compression

import androidx.media3.common.util.UnstableApi
import androidx.media3.transformer.Composition
import androidx.media3.transformer.ExportException
import androidx.media3.transformer.ExportResult
import androidx.media3.transformer.Transformer
import kotlinx.coroutines.CancellableContinuation
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Um [Transformer.Listener] que integra o resultado da compressão com uma [CancellableContinuation] de Coroutine.
 *
 * Esta classe é responsável por resumir a coroutine que aguarda o resultado da compressão.
 *
 * @property continuation A continuação da coroutine que será notificada sobre o sucesso ou falha.
 * @property outputFile O arquivo de saída da compressão. É usado para o resultado de sucesso
 * e para limpeza em caso de falha.
 */
@UnstableApi
class DefaultVideoCompressorTransformerListener(
    private val continuation: CancellableContinuation<File>,
    private val outputFile: File
) : Transformer.Listener {

    /**
     * Callback invocado quando a compressão é concluída com sucesso.
     * Responde à coroutine com o [File] do vídeo comprimido.
     */
    override fun onCompleted(composition: Composition, exportResult: ExportResult) {
        continuation.resume(outputFile)
    }

    /**
     * Callback invocado quando a compressão falha.
     * Deleta o arquivo de saída parcialmente criado e responde à coroutine com a exceção que causou a falha.
     */
    override fun onError(composition: Composition, exportResult: ExportResult, exportException: ExportException) {
        outputFile.delete()
        continuation.resumeWithException(exportException)
    }
}