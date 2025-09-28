package br.com.android.ui.compose.components.video.callbacks

/**
 * Define uma interface funcional para tratar o evento de clique em um vídeo.
 *
 * Esta abordagem permite que o chamador do componente de vídeo decida como
 * a ação de abrir o vídeo será tratada, promovendo o desacoplamento.
 *
 * @author Nikolas Luiz Schmitt
 */
fun interface OnVideoClick {
    /**
     * Executado quando um vídeo é clicado.
     *
     * @param filePath O caminho do arquivo do vídeo que foi clicado.
     * @param onOpenVideo Uma função que, ao ser chamada, deve iniciar a reprodução do vídeo.
     * Esta função recebe o caminho do arquivo como parâmetro.
     */
    fun onExecute(filePath: String, onOpenVideo: (filePath: String) -> Unit)
}