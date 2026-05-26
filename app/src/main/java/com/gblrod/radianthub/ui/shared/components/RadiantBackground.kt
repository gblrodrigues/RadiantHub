package com.gblrod.radianthub.ui.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.gblrod.radianthub.ui.theme.BackgroundOne
import com.gblrod.radianthub.ui.theme.BackgroundThree
import com.gblrod.radianthub.ui.theme.BackgroundTwo
import com.gblrod.radianthub.ui.theme.Purple40
import com.gblrod.radianthub.ui.theme.Purple80
import com.gblrod.radianthub.ui.theme.PurpleGrey40

@Composable
fun RadiantBackground(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        BackgroundOne,
                        BackgroundTwo,
                        BackgroundThree
                    )
                )
            )
    ) {
        content()
    }
}