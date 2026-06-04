package com.gblrod.radianthub.ui.features.maps.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import com.gblrod.radianthub.domain.maps.model.Callout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapCalloutsBottomSheet(
    callouts: List<Callout>,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() }
    ) {
        val groupedCallouts = callouts.groupBy {
                it.superRegionName
            }

        LazyColumn {
            groupedCallouts.forEach { (region, callouts) ->
                item {
                    MapCalloutGroup(
                        title = region,
                        callouts = callouts
                    )
                }
            }
        }
    }
}