package com.gblrod.radianthub.ui.shared.components.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gblrod.radianthub.R
import com.gblrod.radianthub.ui.language.LanguageOptions
import com.gblrod.radianthub.ui.shared.components.SelectionAlertDialog
import com.gblrod.radianthub.ui.theme.ButtonContainerDialog

@Composable
fun LanguageMenu(
    selectedLanguage: LanguageOptions,
    onLanguageSelected: (LanguageOptions) -> Unit,
    onDismiss: () -> Unit
) {
    var currentSelection by remember(selectedLanguage) {
        mutableStateOf(selectedLanguage)
    }

    SelectionAlertDialog(
        title = {
            Text(
                text = stringResource(id = R.string.language_dialog_title)
            )
        },
        text = {
            Column {
                LanguageOptions.entries.forEach { language ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { currentSelection = language }
                    ) {
                        RadioButton(
                            selected = currentSelection == language,
                            onClick = { currentSelection = language },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = ButtonContainerDialog
                            )
                        )
                        Text(
                            text = stringResource(id = language.label),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        },
        onDismiss = { onDismiss() },
        onConfirm = {
            onLanguageSelected(currentSelection)
            onDismiss()
        },
        confirmText = stringResource(id = R.string.dialog_action_confirm),
        dismissText = stringResource(id = R.string.dialog_action_dismiss)
    )
}