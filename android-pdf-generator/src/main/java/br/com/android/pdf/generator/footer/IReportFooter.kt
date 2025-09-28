package br.com.android.pdf.generator.footer

import br.com.android.pdf.generator.common.IDrawable
import br.com.android.pdf.generator.common.IPreparable

/**
 * Interface de marcação para representar o rodapé (Footer) do relatório.
 *
 * O rodapé é a última parte a ser desenhada em cada página, antes de sua finalização.
 *
 * @param FILTER O tipo do objeto de filtro.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IReportFooter<FILTER: Any>: IPreparable<FILTER>, IDrawable