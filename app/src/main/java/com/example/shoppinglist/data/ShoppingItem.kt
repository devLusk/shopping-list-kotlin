package com.example.shoppinglist.data

data class ShoppingItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    var isEditing: Boolean = false
)