package br.com.android.pdf.generator.components.layout

import android.graphics.pdf.PdfDocument
import androidx.core.graphics.withSave
import br.com.android.pdf.generator.common.IPageManager
import br.com.android.pdf.generator.components.IReportComponent
import br.com.android.pdf.generator.extensions.createStaticLayout
import br.com.android.pdf.generator.utils.Margins
import br.com.android.pdf.generator.utils.Paints

/**
 * Um componente [IReportComponent] que exibe uma lista de itens (rótulo/valor) em um layout de grade.
 *
 * A grade é distribuída em colunas, e o componente calcula automaticamente a largura de cada
 * coluna para preencher o espaço disponível na página. Ele também gerencia a quebra de linha
 * e o alinhamento dos itens dentro da grade.
 *
 * @param items A lista de pares (Label: String, Value: String?) a serem exibidos.
 * @param columnCount O número de colunas na grade.
 *
 * @author Nikolas Luiz Schmitt
 */
class LayoutGridComponent<FILTER : Any>(
    private val items: List<Pair<String, String?>>,
    private val columnCount: Int = 3
) : IReportComponent<FILTER> {

    /**
     * Armazena a altura calculada do componente para evitar recálculos.
     */
    private var measuredTotalHeight: Float? = null

    override suspend fun measureHeight(pageManager: IPageManager): Float {
        measuredTotalHeight?.let { return it }

        val config = calculateGridConfig(pageManager.pageInfo)
        val layoutWidth = config.columnWidth.toInt().coerceAtLeast(1)

        val layoutsForMeasurement = items.map { (label, value) ->
            val labelLayout = label.createStaticLayout(
                paint = Paints.defaultLabelPaint,
                width = layoutWidth,
                includePad = false
            )

            val valueLayout = (value ?: "").createStaticLayout(
                paint = Paints.defaultValuePaint,
                width = layoutWidth,
                includePad = false
            )
            labelLayout to valueLayout
        }

        var totalHeight = config.paddingTop
        var columnIndex = 0
        var maxRowHeight = 0f

        layoutsForMeasurement.forEachIndexed { index, (labelLayout, valueLayout) ->
            val labelHeight = labelLayout.height.toFloat()
            val valueHeight = valueLayout.height.toFloat()
            val totalCellHeight = labelHeight + valueHeight + Margins.MARGIN_16

            maxRowHeight = maxOf(maxRowHeight, totalCellHeight)
            columnIndex++

            if (columnIndex == columnCount || index == layoutsForMeasurement.lastIndex) {
                totalHeight += maxRowHeight
                columnIndex = 0
                maxRowHeight = 0f
            }
        }

        this.measuredTotalHeight = totalHeight

        return totalHeight
    }

    override suspend fun draw(pageManager: IPageManager, yStart: Float): Float {
        val config = calculateGridConfig(pageManager.pageInfo)
        val layoutWidth = config.columnWidth.toInt().coerceAtLeast(1)

        var columnIndex = 0
        var rowStartY = yStart + config.paddingTop
        var maxRowHeight = 0f

        this.items.forEachIndexed { index, (label, value) ->
            val labelLayout = label.createStaticLayout(
                paint = Paints.defaultLabelPaint,
                width = layoutWidth,
                includePad = false
            )

            val valueLayout = (value ?: "").createStaticLayout(
                paint = Paints.defaultValuePaint,
                width = layoutWidth,
                includePad = false
            )

            val startX = config.horizontalPaddingStart + (columnIndex * (config.columnWidth + config.columnSpacing))

            val labelHeight = labelLayout.height.toFloat()
            val valueHeight = valueLayout.height.toFloat()
            val totalCellHeight = labelHeight + valueHeight + Margins.MARGIN_16

            rowStartY = pageManager.ensureSpace(rowStartY, totalCellHeight)
            val canvas = pageManager.canvas

            canvas.withSave {
                translate(startX, rowStartY)
                labelLayout.draw(this)
            }

            val valueY = rowStartY + labelHeight + Margins.MARGIN_4
            canvas.withSave {
                translate(startX, valueY)
                valueLayout.draw(this)
            }

            maxRowHeight = maxOf(maxRowHeight, totalCellHeight)

            columnIndex++
            if (columnIndex == columnCount || index == items.lastIndex) {
                rowStartY += maxRowHeight
                columnIndex = 0
                maxRowHeight = 0f
            }
        }

        return rowStartY
    }

    private fun calculateGridConfig(pageInfo: PdfDocument.PageInfo): GridConfig {
        val pageWidth = pageInfo.pageWidth.toFloat()
        val padding = Margins.MARGIN_32.toFloat()
        val spacing = Margins.MARGIN_16.toFloat()

        val usableWidth = pageWidth - (padding * 2) - (spacing * (columnCount - 1))
        val columnWidth = usableWidth / columnCount

        return GridConfig(
            pageWidth = pageWidth,
            horizontalPaddingStart = padding,
            columnSpacing = spacing,
            columnWidth = columnWidth,
            paddingTop = Margins.MARGIN_16.toFloat()
        )
    }
}