package com.gblrod.radianthub.ui.features.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.R
import com.gblrod.radianthub.ui.features.search.model.SearchFilterType
import com.gblrod.radianthub.ui.theme.BlueActions

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    query: String,
    maxChar: Int = 32,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    expanded: Boolean = false,
    onSearchFilterMenu: () -> Unit,
    onDismissFilterMenu: () -> Unit,
    selectedFilter: (SearchFilterType),
    onFilterSelected: (SearchFilterType) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            value = TextFieldValue(
                text = query,
                selection = TextRange(index = query.length)
            )
        )
    }

    LaunchedEffect(query) {
        if (query != textFieldValue.text) {
            textFieldValue = TextFieldValue(
                text = query,
                selection = TextRange(index = query.length)
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            if (it.text.length <= maxChar) {
                textFieldValue = it
                onQueryChange(it.text)
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_placeholder),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        leadingIcon = {
            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        trailingIcon = {
            Row {
                if (query.isNotBlank()) {
                    IconButton(
                        onClick = { onQueryChange("") }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Box {
                    IconButton(
                        onClick = { onSearchFilterMenu() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    SearchFilter(
                        expanded = expanded,
                        selectedFilter = selectedFilter,
                        onDismissRequest = onDismissFilterMenu,
                        onFilterSelected = onFilterSelected
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusRequester.freeFocus()
                focusManager.clearFocus(force = true)
                keyboardController?.hide()
            }
        ),

        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .focusRequester(focusRequester),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BlueActions,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.onSurface
        )
    )
}