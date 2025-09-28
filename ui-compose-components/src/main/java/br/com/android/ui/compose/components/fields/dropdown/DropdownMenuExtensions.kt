package br.com.android.ui.compose.components.fields.dropdown

import android.content.Context
import br.com.core.android.utils.extensions.getLabelFromChronoUnit
import java.time.temporal.ChronoUnit
import kotlin.enums.EnumEntries

/**
 * Converte uma coleção de [ChronoUnit] em uma lista de [MenuItem] para uso em menus suspensos.
 *
 * @param context O contexto do Android, usado para obter os rótulos traduzidos.
 * @return Uma lista de [MenuItem] representando as unidades de tempo.
 *
 * @author Nikolas Luiz Schmitt
 */
fun EnumEntries<ChronoUnit>.getChronoUnitMenuItems(context: Context): List<MenuItem<ChronoUnit?>> {
    val units = slice(ChronoUnit.SECONDS.ordinal..ChronoUnit.HOURS.ordinal)

    return units.map { unit ->
        MenuItem(
            label = unit.getLabelFromChronoUnit(context),
            value = unit
        )
    }
}