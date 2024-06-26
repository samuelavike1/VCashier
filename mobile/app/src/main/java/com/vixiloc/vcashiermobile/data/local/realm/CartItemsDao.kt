package com.vixiloc.vcashiermobile.data.local.realm

import com.vixiloc.vcashiermobile.domain.model.transactions.CartItems
import kotlinx.coroutines.flow.Flow

interface CartItemsDao {
    suspend fun getCartItems(): Flow<List<CartItems>>
    suspend fun insertCartItems(cartItems: CartItems)
    suspend fun updateCartItems(cartItems: CartItems)
    suspend fun deleteCartItems(cartItems: CartItems)
    suspend fun clearCartItems()
}