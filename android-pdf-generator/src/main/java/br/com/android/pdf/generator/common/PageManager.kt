package br.com.android.pdf.generator.common

import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import br.com.android.pdf.generator.enums.EnumPageSize
import br.com.android.pdf.generator.footer.IReportFooter
import br.com.android.pdf.generator.header.IReportHeader

/**
 * Implementação concreta de [IPageManager].
 *
 * Gerencia o ciclo de vida das páginas de um [PdfDocument], coordenando o desenho do
 * cabeçalho e do rodapé em cada página e garantindo que o conteúdo do corpo seja
 * alocado corretamente, quebrando a página quando necessário.
 *
 * @param document A instância do [PdfDocument] onde as páginas serão criadas.
 * @param pageSize As dimensões da página a ser usada (ex: A4).
 * @param header O cabeçalho a ser desenhado no topo de cada página.
 * @param footer O rodapé a ser desenhado na base de cada página.
 *
 * @author Nikolas Luiz Schmitt
 */
class PageManager(
    private val document: PdfDocument,
    private val pageSize: EnumPageSize,
    private val header: IReportHeader<*>,
    private val footer: IReportFooter<*>
) : IPageManager {

    override lateinit var canvas: Canvas
    override lateinit var pageInfo: PdfDocument.PageInfo
    override var currentY: Float = 0f

    private lateinit var currentPage: PdfDocument.Page
    private var pageNumber = 0

    private var headerHeight: Float = 0f
    private var footerHeight: Float = 0f

    override suspend fun start() {
        startNewPage()

        this.headerHeight = header.measureHeight(this)
        this.footerHeight = footer.measureHeight(this)

        this.currentY = drawHeader()
    }

    override suspend fun finish() {
        drawFooter()
        document.finishPage(currentPage)
    }

    override suspend fun ensureSpace(currentY: Float, heightNeeded: Float): Float {
        return if (!hasAvailableSpace(currentY, heightNeeded)) {
            startNewPage()
            drawHeader()
        } else {
            currentY
        }
    }

    override fun hasAvailableSpace(currentY: Float, heightNeeded: Float): Boolean {
        val bottomLimit = pageInfo.pageHeight.toFloat() - this.footerHeight
        return (currentY + heightNeeded) <= bottomLimit
    }

    private suspend fun startNewPage() {
        if (::currentPage.isInitialized) {
            drawFooter()
            document.finishPage(currentPage)
        }

        pageNumber++
        pageInfo = PdfDocument.PageInfo.Builder(pageSize.width, pageSize.height, pageNumber).create()
        currentPage = document.startPage(pageInfo)
        canvas = currentPage.canvas
        currentY = 0f
    }

    private suspend fun drawHeader(): Float {
        return header.draw(this, 0f)
    }

    private suspend fun drawFooter() {
        footer.draw(this, 0f)
    }
}