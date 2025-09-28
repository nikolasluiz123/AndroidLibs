package br.com.android.pdf.generator.body

import br.com.android.pdf.generator.common.IDrawable
import br.com.android.pdf.generator.common.IPreparable
import br.com.android.pdf.generator.session.IReportSession

/**
 * Interface que representa o corpo do relatório, a área principal onde o conteúdo
 * é exibido.
 *
 * O corpo atua como um container para uma ou mais [IReportSession], organizando
 * a estrutura do conteúdo do relatório.
 *
 * @param FILTER O tipo do objeto de filtro.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IReportBody<FILTER: Any>: IPreparable<FILTER>, IDrawable {

    /**
     * A lista de sessões que compõem o corpo do relatório.
     */
    val sessions: List<IReportSession<FILTER>>

    /**
     * O objeto de filtro propagado do relatório, disponível para as sessões.
     */
    val filter: FILTER?

    /**
     * Adiciona uma nova sessão à lista de sessões do corpo do relatório.
     *
     * @param session A instância da sessão a ser adicionada.
     */
    fun addSession(session: IReportSession<FILTER>)
}