package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.R
import com.gblrod.radianthub.ui.shared.components.IndexBadge
import com.gblrod.radianthub.ui.theme.ViewAbilities

@Composable
fun AgentActions(
    onViewSkills: () -> Unit,
    index: Int,
    total: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { onViewSkills() },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ViewAbilities
            )
        ) {
            Text(
                text = stringResource(id = R.string.button_view_skills),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        IndexBadge(
            index = index,
            total = total
        )
    }
}