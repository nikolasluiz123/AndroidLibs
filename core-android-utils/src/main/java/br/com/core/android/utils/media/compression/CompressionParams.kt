package br.com.core.android.utils.media.compression

import java.io.File

/**
 * Encapsula os parâmetros de configuração para a compressão de um vídeo.
 *
 * @property file O [File] do vídeo original que será comprimido.
 * @property targetMaxSizeMb O tamanho máximo alvo para o arquivo de vídeo comprimido, em Megabytes (MB).
 * Este valor é usado para calcular o bitrate ideal para a compressão. O padrão é `10`.
 * @property resolutionHeight A altura máxima desejada para a resolução do vídeo. Se a altura do vídeo
 * original for maior que este valor, o vídeo será redimensionado proporcionalmente.
 * Se for `null`, a resolução original será mantida. O padrão é `null`.
 */
data class CompressionParams(
    val file: File,
    val targetMaxSizeMb: Int = 10,
    val resolutionHeight: Int? = null
)