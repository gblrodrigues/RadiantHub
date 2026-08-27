package com.gblrod.radianthub.ui.features.search.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gblrod.radianthub.ui.features.search.model.SearchFilterType
import com.gblrod.radianthub.ui.theme.NavigationSelected

@Composable
fun SearchFilter(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    selectedFilter: SearchFilterType,
    onDismissRequest: () -> Unit,
    onFilterSelected: (SearchFilterType) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        modifier = modifier,
        onDismissRequest = { onDismissRequest() }
    ) {
        SearchFilterType.entries.forEach { filter ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(id = filter.titleRes),
                        color = if (selectedFilter == filter) NavigationSelected else MaterialTheme.colorScheme.onSurface
                    )
                },
                leadingIcon = {
                    if (selectedFilter == filter) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = NavigationSelected
                        )
                    } else {
                        Icon(
                            imageVector = filter.icon,
                            contentDescription = null
                        )
                    }
                },
                onClick = { onFilterSelected(filter) }
            )
        }
    }
}