package br.com.android.pdf.generator.header

import br.com.android.pdf.generator.common.IDrawable
import br.com.android.pdf.generator.common.IPreparable

/**
 * Interface de marcação para representar o cabeçalho (Header) do relatório.
 *
 * O cabeçalho é a primeira parte a ser desenhada em cada nova página.
 *
 * @param FILTER O tipo do objeto de filtro.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IReportHeader<FILTER: Any>: IPreparable<FILTER>, IDrawable