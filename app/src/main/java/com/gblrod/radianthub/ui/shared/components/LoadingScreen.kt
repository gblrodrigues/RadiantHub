package com.gblrod.radianthub.ui.shared.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.R

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.loading_screen_label),
            color = MaterialTheme.colorScheme.inverseSurface,
            modifier = Modifier.padding(top = 80.dp)
        )
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.inverseSurface
        )
    }
}