package br.com.android.ui.compose.components.video.callbacks

/**
 * Define uma interface funcional para tratar o evento de clique no ícone de exclusão de um vídeo.
 *
 * @author Nikolas Luiz Schmitt
 */
fun interface OnVideoDeleteClick {
    /**
     * Executado quando o botão de exclusão de um vídeo é clicado.
     *
     * @param filePath O caminho do arquivo do vídeo a ser excluído.
     */
    fun onExecute(filePath: String)
}