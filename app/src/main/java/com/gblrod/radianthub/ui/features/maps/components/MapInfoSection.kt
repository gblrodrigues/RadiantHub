package com.gblrod.radianthub.ui.features.maps.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.R
import com.gblrod.radianthub.domain.maps.model.Callout
import com.gblrod.radianthub.domain.maps.model.Maps
import com.gblrod.radianthub.ui.theme.ViewAbilities

@Composable
fun MapInfoSection(
    maps: Maps,
    callouts: List<Callout>?,
    onViewCallouts: () -> Unit
) {
    val hasCallouts = !callouts.isNullOrEmpty()
    val regionCount = callouts
        ?.map { it.superRegionName }
        ?.distinct()
        ?.size ?: 0

    val coordinates = maps.coordinates ?: stringResource(id = R.string.map_no_available_coordinates)

    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.map_information_title),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.map_coordinates_title),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = coordinates,
                style = MaterialTheme.typography.bodyMedium,
                color = if (maps.coordinates.isNullOrBlank()) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = callouts?.size?.toString() ?: "0",
                    label = stringResource(id = R.string.map_callouts_title),
                    color = if (hasCallouts) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )

                StatCard(
                    modifier = Modifier.weight(1f),
                    value = regionCount.toString(),
                    label = stringResource(id = R.string.map_regions_title),
                    color = if (hasCallouts) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (hasCallouts) {
                Button(
                    onClick = { onViewCallouts() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ViewAbilities
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.button_view_callouts),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            } else {
                FilledTonalButton(
                    onClick = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.map_view_no_available_callouts),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}