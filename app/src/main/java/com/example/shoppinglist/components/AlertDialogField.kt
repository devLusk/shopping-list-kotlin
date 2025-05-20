package com.example.shoppinglist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AlertDialogField(
    itemName: String,
    onItemNameChange: (String) -> Unit,
    itemQuantity: String,
    onItemQuantityChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.padding(16.dp),
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Add Shopping Item",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { onItemNameChange(it) },
                    label = { Text("Item Name") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = itemQuantity,
                    onValueChange = { onItemQuantityChange(it) },
                    label = { Text("Item Quantity") }
                )
            }
        },

        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismissRequest()
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = { onDismissRequest() },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Cancel")
            }
        }
    )
}