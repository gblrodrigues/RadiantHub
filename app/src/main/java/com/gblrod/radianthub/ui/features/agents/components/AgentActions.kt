package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gblrod.radianthub.R
import com.gblrod.radianthub.ui.theme.ViewAbilities

@Composable
fun AgentActions(
    onViewSkills: () -> Unit
) {
    Button(
        onClick = { onViewSkills() },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ViewAbilities
        )
    ) {
        Text(
            text = stringResource(id = R.string.view_skills),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}