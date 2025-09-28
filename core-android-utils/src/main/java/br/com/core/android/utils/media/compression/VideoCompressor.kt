package br.com.core.android.utils.media.compression

import android.content.Context
import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.util.UnstableApi
import androidx.media3.effect.ScaleAndRotateTransformation
import androidx.media3.transformer.DefaultEncoderFactory
import androidx.media3.transformer.EditedMediaItem
import androidx.media3.transformer.Effects
import androidx.media3.transformer.Transformer
import androidx.media3.transformer.VideoEncoderSettings
import br.com.core.android.utils.media.FileUtils
import br.com.core.android.utils.media.VideoUtils
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File

/**
 * Orquestra a compressão de vídeos utilizando a biblioteca Media3 Transformer.
 *
 * Esta classe é projetada para ser usada dentro de uma Coroutine, pois a compressão
 * é um processo assíncrono.
 *
 * @property context O [Context] da aplicação, necessário para o Transformer e para a manipulação de arquivos.
 */
@OptIn(UnstableApi::class)
class VideoCompressor(private val context: Context) {

    /**
     * Inicia o processo de compressão de um vídeo com base nos parâmetros fornecidos.
     *
     * A função suspende a coroutine até que a compressão seja concluída ou falhe.
     * Ela é cancelável, interrompendo o processo do `Transformer` se a coroutine for cancelada.
     *
     * @param params Os [CompressionParams] que definem o arquivo de origem e as configurações de compressão.
     * @return O [File] do vídeo comprimido com sucesso.
     * @throws androidx.media3.transformer.ExportException Se ocorrer um erro durante a compressão.
     */
    suspend fun compress(params: CompressionParams): File = suspendCancellableCoroutine { continuation ->
        val originalFileName = FileUtils.getFileNameWithExtensionFromFilePath(params.file.absolutePath)
        val compressedFileName = VideoUtils.getCompressedVideoFileName(originalFileName)
        val outputFile = VideoUtils.createVideoFile(context, compressedFileName)

        val durationInSeconds = VideoUtils.getVideoDurationInSeconds(params.file)
        val targetBitrate = ((params.targetMaxSizeMb * 1024 * 1024 * 8) / durationInSeconds).toInt()

        val mediaItem = MediaItem.fromUri(Uri.fromFile(params.file))
        val editedMediaItem = getEditedMediaItem(params, mediaItem)

        val encoderFactory = getEncoderFactory(targetBitrate)

        val transformerBuilder = Transformer.Builder(context)
            .setEncoderFactory(encoderFactory)
            .setVideoMimeType(MimeTypes.VIDEO_H264)
            .setAudioMimeType(MimeTypes.AUDIO_AAC)
            .addListener(DefaultVideoCompressorTransformerListener(continuation, outputFile))

        val transformer = transformerBuilder.build()

        transformer.start(editedMediaItem, outputFile.absolutePath)

        continuation.invokeOnCancellation {
            transformer.cancel()
        }
    }

    /**
     * Prepara o [EditedMediaItem] para a transformação. Se um `resolutionHeight` for especificado
     * e for menor que a altura do vídeo original, aplica um efeito de redimensionamento.
     *
     * @param params Os parâmetros de compressão.
     * @param mediaItem O [MediaItem] do vídeo original.
     * @return Um [EditedMediaItem] com ou sem efeitos de redimensionamento.
     */
    private fun getEditedMediaItem(params: CompressionParams, mediaItem: MediaItem): EditedMediaItem {
        return if (params.resolutionHeight != null) {
            val originalResolution = VideoUtils.getVideoResolution(params.file)

            if (originalResolution != null && originalResolution.second > params.resolutionHeight) {
                val scale = params.resolutionHeight.toFloat() / originalResolution.second.toFloat()

                val scaleEffect = ScaleAndRotateTransformation.Builder()
                    .setScale(scale, scale)
                    .build()

                val effects = Effects(listOf(), listOf(scaleEffect))

                EditedMediaItem.Builder(mediaItem).setEffects(effects).build()
            } else {
                EditedMediaItem.Builder(mediaItem).build()
            }
        } else {
            EditedMediaItem.Builder(mediaItem).build()
        }
    }

    /**
     * Cria uma fábrica de encoders ([DefaultEncoderFactory]) configurada com o bitrate de vídeo alvo.
     *
     * @param targetBitrate O bitrate em bits por segundo a ser usado pelo encoder de vídeo.
     * @return A instância do [DefaultEncoderFactory] configurada.
     */
    private fun getEncoderFactory(targetBitrate: Int): DefaultEncoderFactory {
        val videoEncoderSettings = VideoEncoderSettings.Builder()
            .setBitrate(targetBitrate)
            .build()

        return DefaultEncoderFactory.Builder(context)
            .setRequestedVideoEncoderSettings(videoEncoderSettings)
            .build()
    }
}