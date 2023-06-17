package com.example.app.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.app.R
import com.example.domain.database.model.CartItem
import com.example.domain.network.model.Dish

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishAlertDialog(
    openDialog: Boolean,
    dish: Dish?,
    addToCart: (cartItem: CartItem) -> Unit,
    onDismissRequest: () -> Unit
) {
    if (openDialog && dish != null) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
        ) {
            Card(
                Modifier
//                .size(343.dp,446.dp)
                    .fillMaxWidth(0.99f)
                    .padding(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                shape = RoundedCornerShape(15.dp),
            ) {

                Column(
                    modifier = Modifier.padding(12.dp),
                    //   horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Card() {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(198.dp, 204.dp)
                                    .padding(8.dp),
                                model = dish.imageUrl,
                                contentDescription = stringResource(R.string.dish_image),
                            )
                            Row(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .align(Alignment.TopEnd),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                IconButton(
                                    modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                                    onClick = { /*TODO*/ },
                                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.secondary)

                                ) {
                                    Image(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = stringResource(
                                            R.string.add_to_favorite
                                        )
                                    )
                                }
                                IconButton(
                                    modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                                    onClick = { onDismissRequest() },
                                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.secondary)
                                ) {
                                    Image(
                                        imageVector = Icons.Outlined.Close,
                                        contentDescription = stringResource(
                                            R.string.close
                                        )
                                    )
                                }
                            }
                        }
                    }
                    Text(text = dish.name, style = MaterialTheme.typography.bodyLarge)
                    Row(
                        modifier = Modifier.align(Alignment.Start),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "${dish.price} ₽", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "${dish.weight}г", style = MaterialTheme.typography.labelMedium)

                    }
                    Text(text = dish.description, style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            addToCart(
                                CartItem(
                                    id = dish.id,
                                    quantity = 1,
                                    price = dish.price,
                                    weight = dish.weight,
                                    imageUrl = dish.imageUrl,
                                    name = dish.name
                                )
                            )
                            onDismissRequest()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp),
                            text = stringResource(R.string.add_to_cart),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )

                    }
                }
            }


        }
    }

}