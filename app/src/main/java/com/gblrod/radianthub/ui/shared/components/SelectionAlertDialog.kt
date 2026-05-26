package com.gblrod.radianthub.ui.shared.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.gblrod.radianthub.ui.theme.ButtonContainerDialog

@Composable
fun SelectionAlertDialog(
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    onConfirm: () -> Unit,
    confirmText: String,
    onDismiss: () -> Unit,
    dismissText: String,
    content: (@Composable () -> Unit)? = null
) {

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = RoundedCornerShape(16.dp)
            ),
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { onDismiss() },
        title = { title() },
        text = {
            Column {
                text()
                content?.invoke()
            }
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = ButtonContainerDialog),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = confirmText,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = ButtonContainerDialog
                ),
            ) {
                Text(
                    text = dismissText,
                    color = ButtonContainerDialog
                )
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}