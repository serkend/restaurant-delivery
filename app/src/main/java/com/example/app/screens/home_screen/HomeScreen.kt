package com.example.app.screens.home_screen

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.app.HomeHeader
import com.example.app.R
import com.example.app.screens.home_screen.components.ChipGroup
import com.example.app.screens.home_screen.components.CustomSnackbar
import com.example.app.screens.home_screen.components.DishAlertDialog
import com.example.app.screens.home_screen.state.DishesUIState
import com.example.app.ui.theme.CartdDishItemColor
import com.example.app.utils.Constants
import com.example.domain.database.model.CartItem
import com.example.domain.network.model.Dish

@Composable
fun HomeScreen(viewModel: HomeViewModel, scaffoldState: ScaffoldState) {
    val state = viewModel.uiState.collectAsState().value
    val dishes = viewModel.cachedDishesState.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var openDialog by remember {
            mutableStateOf(false)
        }
        var selectedDish by remember {
            mutableStateOf<Dish?>(null)
        }
        HomeHeader()
        val listState: LazyListState = rememberLazyListState()
        val isSticked: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        val alphaValue: Float by animateFloatAsState(
            if (isSticked) 1f else 0f
        )
        Box(modifier = Modifier.fillMaxSize()) {
            DishesList(dishes = dishes,
                listState = listState,
                isSticked = isSticked,
                dishClicked = {
                    selectedDish = it
                    openDialog = true
                }, sortDishesByTag = {
                    viewModel.sortDishesByTag(it)
                }, addToCart = { viewModel.addToCart(it) })
            if (isSticked)
                ChipGroup(tags = Constants.TAG_LIST, alphaValue = alphaValue) {
                    viewModel.sortDishesByTag(it)
                }
            DishAlertDialog(openDialog = openDialog,
                dish = selectedDish,
                addToCart = { item ->
                    viewModel.addToCart(item)
                },
                onDismissRequest = {
                    openDialog = false
                })
        }
    }
    when (state) {
        DishesUIState.Loading -> {

        }

        is DishesUIState.ShowSnackbar -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                CustomSnackbar(
                    scaffoldState = scaffoldState,
                    snackbarData = "No internet connection!"
                )
            }
        }
    }

}

@Composable
fun DishesList(
    dishes: List<Dish>,
    listState: LazyListState,
    isSticked: Boolean,
    dishClicked: (Dish) -> Unit,
    addToCart: (CartItem) -> Unit,
    sortDishesByTag: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
        item { ExpandedTopBar() }

        item {
            val alphaValue: Float by animateFloatAsState(
                if (!isSticked) 1f else 0f
            )
            ChipGroup(tags = Constants.TAG_LIST, alphaValue = alphaValue) {
                sortDishesByTag(it)
            }
        }
        items(dishes) { dish ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .clickable {
                        dishClicked(dish)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors(containerColor = CartdDishItemColor)
                ) {
                    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                        AsyncImage(
                            modifier = Modifier
                                .size(160.dp)
                                .padding(8.dp),
                            model = dish.imageUrl,
                            contentDescription = stringResource(R.string.dish_image),
                        )
                    }
//                }
                }
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        modifier = Modifier,
                        text = dish.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        modifier = Modifier,
                        text = dish.description,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                    OutlinedButton(
                        modifier = Modifier.align(Alignment.End),
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
                        },
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.price_button, dish.price),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ExpandedTopBar() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        items(Constants.DRAWABLE_LIST) { drawableId ->
            Image(
                modifier = Modifier
                    .size(300.dp, 112.dp),
                painter = painterResource(id = drawableId),
                contentDescription = stringResource(R.string.discount_banner),
                contentScale = ContentScale.Crop
            )
        }
    }
}