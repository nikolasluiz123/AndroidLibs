package br.com.android.pdf.generator.report

import android.graphics.pdf.PdfDocument
import br.com.android.pdf.generator.body.IReportBody
import br.com.android.pdf.generator.common.PageManager
import br.com.android.pdf.generator.enums.EnumPageSize
import br.com.android.pdf.generator.footer.IReportFooter
import br.com.android.pdf.generator.header.IReportHeader

/**
 * Classe abstrata que define a estrutura e o ciclo de vida de um relatório PDF.
 *
 * Orquestra o processo de geração, dividindo o relatório em três partes principais:
 * [header], [body] e [footer]. As classes filhas devem implementar o método [initialize]
 * para construir e configurar essas três partes.
 *
 * @property filter O objeto de filtro usado para gerar o relatório. Ele é propagado para
 * todas as partes do relatório ([header], [body], [footer]) durante a fase de preparação.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractPDFReport<FILTER: Any>(var filter: FILTER) {

    protected lateinit var header: IReportHeader<FILTER>
    protected lateinit var body: IReportBody<FILTER>
    protected lateinit var footer: IReportFooter<FILTER>

    /**
     * Inicializa os componentes do relatório (`header`, `body`, `footer`).
     *
     * Este método deve ser implementado para instanciar e atribuir as implementações
     * concretas das partes do relatório. É o primeiro passo no ciclo de vida da geração.
     */
    protected abstract suspend fun initialize()

    /**
     * Executa o método `prepare` em cascata para cada parte do relatório (header, body, footer).
     *
     * Esta fase garante que todos os dados necessários sejam carregados antes do início do
     * processo de desenho.
     */
    protected suspend fun prepare() {
        header.prepare(filter)
        body.prepare(filter)
        footer.prepare(filter)
    }

    /**
     * Ponto de entrada principal que orquestra todo o processo de geração do relatório.
     *
     * O fluxo de execução é: `initialize()` -> `prepare()` -> desenho com [PageManager].
     *
     * @param document A instância do [PdfDocument] onde o relatório será renderizado.
     * @param pageSize O tamanho da página a ser usado para o relatório.
     *
     * @see [PageManager]
     */
    suspend fun generate(document: PdfDocument, pageSize: EnumPageSize) {
        initialize()
        prepare()

        val pageManager = PageManager(document, pageSize, header, footer)
        pageManager.start()

        body.draw(pageManager, pageManager.currentY)

        pageManager.finish()
    }
}