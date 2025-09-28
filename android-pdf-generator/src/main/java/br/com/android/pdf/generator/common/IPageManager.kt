package br.com.android.pdf.generator.common

import android.graphics.Canvas
import android.graphics.pdf.PdfDocument

/**
 * Define um contrato para o gerenciador de páginas de um relatório PDF.
 *
 * A principal responsabilidade desta interface é abstrair o controle sobre o ciclo de vida
 * das páginas. Ela gerencia a criação de novas páginas, o desenho de cabeçalhos e rodapés,
 * e garante que os componentes do corpo do relatório tenham espaço suficiente para serem
 * desenhados, quebrando a página quando necessário.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IPageManager {

    /**
     * O [Canvas] da página atual no qual o conteúdo deve ser desenhado.
     * A API do [PdfDocument] fornece um `Canvas` diferente para cada página.
     */
    val canvas: Canvas

    /**
     * Contém informações sobre a página atual, como dimensões e número da página.
     * Útil para cálculos de layout dentro dos componentes [IDrawable].
     */
    val pageInfo: PdfDocument.PageInfo

    /**
     * A posição vertical (eixo Y) atual na página, indicando onde o próximo
     * elemento deve começar a ser desenhado.
     */
    var currentY: Float

    /**
     * Garante que haja espaço vertical suficiente na página atual para desenhar um elemento
     * com a altura especificada.
     *
     * Se não houver espaço, esta função deve finalizar a página atual (desenhando o rodapé),
     * iniciar uma nova página (desenhando o cabeçalho) e retornar a posição Y inicial
     * da nova página. Se houver espaço, a `currentY` original é retornada.
     *
     * @param currentY A posição Y atual onde se pretende desenhar.
     * @param heightNeeded A altura total necessária para o próximo elemento.
     * @return A posição Y ajustada para o desenho (pode ser na mesma página ou em uma nova).
     */
    suspend fun ensureSpace(currentY: Float, heightNeeded: Float): Float

    /**
     * Verifica se há espaço vertical disponível na página para desenhar um elemento.
     *
     * @param currentY A posição Y atual.
     * @param heightNeeded A altura do elemento a ser desenhado.
     * @return `true` se houver espaço, `false` caso contrário.
     */
    fun hasAvailableSpace(currentY: Float, heightNeeded: Float): Boolean

    /**
     * Inicia o processo de paginação do documento.
     *
     * Deve criar a primeira página e desenhar seu cabeçalho.
     */
    suspend fun start()

    /**
     * Finaliza a última página do documento.
     *
     * Deve desenhar o rodapé da última página e fechar o objeto da página.
     */
    suspend fun finish()
}