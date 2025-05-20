package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.components.AlertDialogField
import com.example.shoppinglist.components.ShoppingListItem
import com.example.shoppinglist.data.ShoppingItem
import com.example.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                ShoppingListHome()
            }
        }
    }
}

@Composable
fun ShoppingListHome(modifier: Modifier = Modifier) {
    var sItem by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }

    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 23.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    onClick = { showDialog = true }
                ) {
                    Text("Add Item")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    onClick = { sItem = emptyList() }
                ) {
                    Text("Delete All")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Shopping List",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start),
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
                    .padding(8.dp)
            ) {
                if (sItem.isEmpty()) {
                    Text(
                        text = "No items added.",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Gray
                    )
                } else {
                    LazyColumn {
                        items(sItem) { item ->
                            ShoppingListItem(
                                item = item,
                                onDelete = { sItem -= item }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialogField(
            onDismissRequest = { showDialog = false },
            itemName = itemName,
            onItemNameChange = { itemName = it },
            itemQuantity = itemQuantity,
            onItemQuantityChange = { itemQuantity = it },
            onConfirm = {
                val quantity = itemQuantity.toIntOrNull()
                if (itemName.isNotBlank() && quantity != null) {
                    val newItem = ShoppingItem(
                        id = sItem.size + 1,
                        name = itemName,
                        quantity = quantity
                    )
                    sItem += newItem
                    itemName = ""
                    itemQuantity = ""
                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ShoppingListPreview() {
    ShoppingListTheme {
        ShoppingListHome()
    }
}
