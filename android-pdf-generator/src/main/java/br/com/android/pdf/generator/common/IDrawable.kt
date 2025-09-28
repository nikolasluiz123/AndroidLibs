package br.com.android.pdf.generator.common

/**
 * Define um contrato para qualquer elemento do relatório que pode ser desenhado em uma página PDF.
 *
 * O processo de desenho é dividido em duas fases:
 * 1.  **Medição (`measureHeight`):** Calcular a altura total que o elemento ocupará.
 * Isso é crucial para o [IPageManager] determinar se há espaço suficiente na
 * página atual ou se uma nova página é necessária.
 * 2.  **Desenho (`draw`):** Renderizar o elemento no [Canvas] da página.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IDrawable {

    /**
     * Calcula e retorna a altura total (em pixels) que este elemento ocupará no PDF.
     *
     * É uma boa prática que a implementação armazene (cache) o resultado deste cálculo
     * para evitar recálculos desnecessários, especialmente para componentes complexos.
     *
     * @param pageManager O gerenciador de páginas do relatório, que fornece o contexto
     * da página para o cálculo.
     * @return A altura total do elemento em pixels.
     */
    suspend fun measureHeight(pageManager: IPageManager): Float

    /**
     * Desenha o elemento no [Canvas] da página, começando a partir da posição vertical (`yStart`)
     * fornecida.
     *
     * @param pageManager O gerenciador de páginas, que fornece acesso ao [Canvas] e
     * outras informações da página.
     * @param yStart A posição no eixo Y onde o desenho deve começar.
     * @return A nova posição Y após o desenho do elemento, que será usada como `yStart`
     * para o próximo elemento.
     */
    suspend fun draw(pageManager: IPageManager, yStart: Float): Float
}