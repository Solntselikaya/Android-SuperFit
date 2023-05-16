package com.example.superfit.presentation.common.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.constrain

fun Modifier.onMeasureConstraints(
    block: (Constraints) -> Unit
) = layout { measurable, constraints ->
    // record the constraints *before* measuring so that they're available during recursive measurement
    block(constraints)
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        placeable.place(0, 0)
    }
}
fun Modifier.constrainSize(
    getConstraints: () -> Constraints
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints.constrain(getConstraints()))
    layout(placeable.width, placeable.height) {
        placeable.place(0, 0)
    }
}