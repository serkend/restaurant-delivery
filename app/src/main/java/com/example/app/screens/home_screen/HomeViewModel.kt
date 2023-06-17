package com.example.app.screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.screens.home_screen.state.DishesUIState
import com.example.common.ResponseStatus
import com.example.domain.database.model.CartItem
import com.example.domain.database.usecases.cart.SaveCartItem
import com.example.domain.database.usecases.dish.DishUseCases
import com.example.domain.network.model.Dish
import com.example.domain.network.model.Dishes
import com.example.domain.network.usecases.GetDishes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDishes: GetDishes,
    private val saveCartItem: SaveCartItem,
    private val dishUseCases: DishUseCases
) : ViewModel() {

    private var _uiState: MutableStateFlow<DishesUIState<Dishes>> =
        MutableStateFlow(DishesUIState.Loading)
    val uiState = _uiState.asStateFlow()

    private var _cachedDishesState: MutableStateFlow<List<Dish>> =
        MutableStateFlow(emptyList())
    val cachedDishesState = _cachedDishesState.asStateFlow()

    private var dishes: List<Dish>? = null

    init {
        getAllDishes()
        getCachedDishes()
    }

    private fun getAllDishes() {
        viewModelScope.launch {
            getDishes().collect { state ->
                when (state) {
                    is ResponseStatus.Error -> {
                        _uiState.value = DishesUIState.ShowSnackbar(error = state.error)
                    }

                    ResponseStatus.Loading -> {
                        _uiState.value = DishesUIState.Loading
                    }

                    is ResponseStatus.Success -> {
                        state.data.dishes.forEach { dishUseCases.saveCachedDish(it) }
                    }
                }
            }
        }
    }

    fun sortDishesByTag(tag: String) {
        if (_cachedDishesState.value.isNotEmpty()) {
            _cachedDishesState.value = dishes?.filter { it.tegs.contains(tag) } ?: emptyList()
        }
    }

    fun addToCart(cartItem: CartItem) {
        viewModelScope.launch {
            saveCartItem(cartItem)
        }
    }

    private fun getCachedDishes() {
        viewModelScope.launch {
            dishUseCases.getCachedDishes().collect{
                _cachedDishesState.value = it
                dishes = it
            }
        }
    }
}