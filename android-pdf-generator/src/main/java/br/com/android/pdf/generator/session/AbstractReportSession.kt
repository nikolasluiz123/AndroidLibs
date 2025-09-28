package br.com.android.pdf.generator.session

import android.content.Context
import android.graphics.Canvas
import android.text.TextPaint
import br.com.android.pdf.generator.common.IPageManager
import br.com.android.pdf.generator.components.IReportComponent
import br.com.android.pdf.generator.utils.Margins
import br.com.android.pdf.generator.utils.Paints

/**
 * Implementação base para uma sessão de relatório ([IReportSession]).
 *
 * Fornece uma estrutura padrão para uma sessão, que consiste em:
 * - Um título.
 * - Uma linha horizontal abaixo do título.
 * - Uma lista de [IReportComponent] que formam o conteúdo da sessão.
 *
 * Gerencia o ciclo de desenho para o título, a linha e todos os componentes filhos.
 *
 * @param context O [Context] da aplicação.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractReportSession<FILTER : Any>(
    protected val context: Context
) : IReportSession<FILTER> {

    protected lateinit var title: String
    protected var components: List<IReportComponent<FILTER>> = emptyList()

    /**
     * Retorna o [TextPaint] a ser usado para desenhar o título da sessão.
     * Permite que subclasses personalizem a aparência do título.
     */
    open fun getTitlePaint(): TextPaint = Paints.subtitlePaint

    override suspend fun measureHeight(pageManager: IPageManager): Float {
        val titleHeight = getTitlePaint().textSize + Margins.MARGIN_8
        val lineHeight = Margins.MARGIN_8
        var totalHeight = Margins.MARGIN_12 + titleHeight + lineHeight

        for (component in components) {
            totalHeight += component.measureHeight(pageManager)
        }

        return totalHeight
    }

    override suspend fun draw(pageManager: IPageManager, yStart: Float): Float {
        val pageWidth = pageManager.pageInfo.pageWidth.toFloat()
        val paddingStart = Margins.MARGIN_32.toFloat()
        val newYStart = yStart + Margins.MARGIN_12.toFloat()

        val titleHeight = getTitlePaint().textSize + Margins.MARGIN_8
        val lineHeight = Margins.MARGIN_8
        val headerHeight = titleHeight + lineHeight

        val currentY = pageManager.ensureSpace(newYStart, headerHeight)

        val titleY = drawTitle(paddingStart, currentY, pageManager.canvas)
        val lineY = drawLine(titleY, pageManager.canvas, paddingStart, pageWidth)

        return drawComponents(lineY, pageManager)
    }

    private suspend fun drawComponents(lineY: Float, pageManager: IPageManager): Float {
        var currentY = lineY

        components.forEach { component ->
            currentY = component.draw(pageManager, currentY)
        }

        return currentY
    }

    private fun drawTitle(paddingStart: Float, yStart: Float, canvas: Canvas): Float {
        val titleX = paddingStart
        val titleY = yStart + getTitlePaint().textSize + Margins.MARGIN_8

        canvas.drawText(
            title,
            titleX,
            titleY,
            getTitlePaint()
        )
        return titleY
    }

    private fun drawLine(titleY: Float, canvas: Canvas, paddingStart: Float, pageWidth: Float): Float {
        val lineStartY = titleY + Margins.MARGIN_8

        canvas.drawLine(
            paddingStart,
            lineStartY,
            pageWidth - paddingStart,
            lineStartY,
            Paints.titleLinePaint
        )

        return lineStartY
    }
}