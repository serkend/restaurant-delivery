package com.example.app.screens.cart_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.app.R
import com.example.app.ui.theme.CartdDishItemColor
import com.example.domain.database.model.CartItem

@Composable
fun CartScreen() {
    val viewModel: CartViewModel = hiltViewModel()
    val cartItems = viewModel.uiState.collectAsState().value
    val totalPrice = viewModel.totalPrice

    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.SpaceBetween) {
        LazyColumn(modifier = Modifier) {
            items(cartItems) { item ->
                CartRow(
                    cartItem = item,
                    incrementCartItem = { viewModel.addToCartDish(item.copy(quantity = item.quantity + 1)) },
                    decrementCartItem = { viewModel.addToCartDish(item.copy(quantity = item.quantity - 1)) },
                    deleteCartItem = { viewModel.deleteCartItem(item) },
                )
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = stringResource(id = R.string.pay, totalPrice.value),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

            }
        }

    }

}

@Composable
fun CartRow(
    cartItem: CartItem,
    incrementCartItem: (CartItem) -> Unit,
    decrementCartItem: (CartItem) -> Unit,
    deleteCartItem: (CartItem) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.weight(0.5f), verticalAlignment = Alignment.CenterVertically) {
            Card(
                modifier = Modifier,
                colors = CardDefaults.cardColors(containerColor = CartdDishItemColor)
            ) {
                Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                    AsyncImage(
                        modifier = Modifier
                            .size(94.dp)
                            .padding(8.dp),
                        model = cartItem.imageUrl,
                        contentDescription = stringResource(R.string.dish_image),
                    )
                }
            }

            Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    modifier = Modifier,
                    text = cartItem.name,
                    style = MaterialTheme.typography.bodySmall
                )
                Row(
                    modifier = Modifier.align(Alignment.Start),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "${cartItem.price * cartItem.quantity} ₽",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "${cartItem.weight * cartItem.quantity}г",
                        style = MaterialTheme.typography.labelMedium
                    )

                }

            }
        }
        Card(colors = CardDefaults.cardColors(containerColor = CartdDishItemColor)) {
            Row(
                modifier = Modifier.padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        if (cartItem.quantity != 1) decrementCartItem(cartItem)
                        else deleteCartItem(cartItem)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "-", style = MaterialTheme.typography.headlineMedium)
                }
                Text(text = "${cartItem.quantity}", style = MaterialTheme.typography.bodyLarge)
                Button(
                    onClick = { incrementCartItem(cartItem) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "+", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }
    }
}