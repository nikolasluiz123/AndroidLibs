package br.com.android.ui.compose.components.list.grouped.expandable

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.android.ui.compose.components.R
import br.com.android.ui.compose.components.divider.BaseHorizontalDivider
import br.com.android.ui.compose.components.label.LabeledText

/**
 * Um cabeçalho de seção expansível básico, com um rótulo, um valor e uma seta que gira para indicar o estado expandido/recolhido.
 *
 * @param label O rótulo principal da seção.
 * @param value O valor ou texto secundário a ser exibido.
 * @param isExpanded `true` se a seção estiver expandida, `false` caso contrário.
 * @param modifier O [Modifier] a ser aplicado ao [ConstraintLayout].
 * @param onClick A lambda a ser executada quando a seção for clicada.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BasicExpandableSection(
    label: String,
    value: String,
    isExpanded: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    val transition = updateTransition(targetState = isExpanded, label = "expandTransition")

    val arrowRotation by transition.animateFloat(
        label = "arrowRotation",
        transitionSpec = { tween(durationMillis = Spring.StiffnessMediumLow.toInt()) }
    ) { expanded ->
        if (expanded) 180f else 0f
    }

    ConstraintLayout(
        modifier
            .clickable { onClick() }
    ) {
        val (textRef, iconRef, dividerRef) = createRefs()

        LabeledText(
            modifier = Modifier
                .constrainAs(textRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            label = label,
            value = value
        )

        Icon(
            modifier = Modifier
                .constrainAs(iconRef) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(end = 8.dp)
                .rotate(arrowRotation),
            painter = painterResource(id =  R.drawable.ic_expand_more),
            contentDescription = null
        )

        BaseHorizontalDivider(
            modifier = Modifier.constrainAs(dividerRef) {
                bottom.linkTo(parent.bottom)
                top.linkTo(textRef.bottom, margin = 8.dp)
            },
        )
    }
}