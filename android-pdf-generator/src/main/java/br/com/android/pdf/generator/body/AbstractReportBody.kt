package br.com.android.pdf.generator.body

import br.com.android.pdf.generator.common.IPageManager
import br.com.android.pdf.generator.session.IReportSession

/**
 * Implementação base para o corpo de um relatório ([IReportBody]).
 *
 * Esta classe gerencia uma lista de [IReportSession] e orquestra seus ciclos de vida
 * de preparação, medição e desenho. Ela também é responsável por filtrar as sessões
 * que devem ser renderizadas com base na lógica de `shouldRender` de cada sessão.
 *
 * Na maioria dos casos, esta implementação abstrata será suficiente, não necessitando
 * de subclasses.
 *
 * @param FILTER O tipo do objeto de filtro.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractReportBody<FILTER: Any>: IReportBody<FILTER> {

    override val sessions = mutableListOf<IReportSession<FILTER>>()
    final override var filter: FILTER? = null
        private set

    override fun addSession(session: IReportSession<FILTER>) {
        this.sessions.add(session)
    }

    override suspend fun prepare(filter: FILTER) {
        super.prepare(filter)

        this.filter = filter
        sessions.forEach { it.prepare(filter) }
    }

    override suspend fun measureHeight(pageManager: IPageManager): Float {
        var totalHeight = 0f

        getRenderableSessions().forEach { session ->
            totalHeight += session.measureHeight(pageManager)
        }

        return totalHeight
    }

    override suspend fun draw(pageManager: IPageManager, yStart: Float): Float {
        var currentY = yStart

        getRenderableSessions().forEach { session ->
            currentY = session.draw(pageManager, currentY)
        }

        return currentY
    }

    private fun getRenderableSessions(): List<IReportSession<FILTER>> {
        return sessions.filter { it.shouldRender(filter!!) }
    }
}