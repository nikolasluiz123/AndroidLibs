package br.com.android.pdf.generator.footer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.RectF
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import br.com.android.pdf.generator.R
import br.com.android.pdf.generator.common.IPageManager
import br.com.core.utils.enums.EnumDateTimePatterns
import br.com.core.utils.extensions.format
import br.com.android.pdf.generator.utils.Margins
import br.com.android.pdf.generator.utils.Paints
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Implementação padrão para o rodapé do relatório ([IReportFooter]).
 *
 * Este rodapé exibe um layout comum com:
 * - Um logotipo à esquerda.
 * - A data e hora da geração no centro.
 * - O número da página atual à direita.
 * - Uma linha horizontal separando o rodapé do corpo do relatório.
 *
 * @param context O [Context] da aplicação.
 * @param reportLogo O ID do recurso drawable (`R.drawable.*`) para o logotipo.
 *
 * @author Nikolas Luiz Schmitt
 */
class DefaultReportFooter<FILTER : Any>(
    private val context: Context,
    private val reportLogo: Int
) : IReportFooter<FILTER> {

    private lateinit var date: LocalDateTime
    private lateinit var bitmap: Bitmap

    private val bottomPadding = Margins.MARGIN_32.toFloat()
    private val logoSize = 40f
    private val lineSpacing = Margins.MARGIN_8.toFloat()

    override suspend fun prepare(filter: FILTER) {
        super.prepare(filter)
        this.bitmap = AppCompatResources.getDrawable(context, reportLogo)?.toBitmap()!!
        this.date = LocalDateTime.now(ZoneId.systemDefault())
    }

    override suspend fun measureHeight(pageManager: IPageManager): Float {
        return lineSpacing + logoSize + bottomPadding
    }

    override suspend fun draw(pageManager: IPageManager, yStart: Float): Float {
        val pageInfo = pageManager.pageInfo
        val canvas = pageManager.canvas
        val sidePadding = Margins.MARGIN_32.toFloat()

        val contentBottomY = pageInfo.pageHeight - bottomPadding

        val lineY = contentBottomY - logoSize - lineSpacing

        canvas.drawLine(
            sidePadding,
            lineY,
            pageInfo.pageWidth - sidePadding,
            lineY,
            Paints.titleLinePaint
        )

        val logoRect = RectF(
            sidePadding,
            contentBottomY - logoSize,
            sidePadding + logoSize,
            contentBottomY
        )

        canvas.drawBitmap(bitmap, null, logoRect, null)

        val textPaint = Paints.defaultValuePaint
        val formattedDateTime = date.format(EnumDateTimePatterns.DATE_TIME_SHORT)
        val dateText = context.getString(R.string.default_report_footer_generated_date_time, formattedDateTime)
        val pageNumberText = context.getString(R.string.default_report_footer_page_number, pageInfo.pageNumber)

        val dateTextY = contentBottomY - ((logoSize - textPaint.textSize) / 2)
        canvas.drawText(dateText, logoRect.right + Margins.MARGIN_8, dateTextY, textPaint)

        val pageTextWidth = textPaint.measureText(pageNumberText)
        val pageTextX = pageInfo.pageWidth - pageTextWidth - sidePadding
        val pageTextY = dateTextY
        canvas.drawText(pageNumberText, pageTextX, pageTextY, textPaint)

        return yStart
    }
}