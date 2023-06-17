package com.example.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.navigation.Navigation
import com.example.app.navigation.Screens
import com.example.app.screens.home_screen.HomeViewModel
import com.example.app.screens.home_screen.components.ChipGroup
import com.example.app.screens.home_screen.components.Spinner
import com.example.app.ui.theme.SelectedItemColor
import com.example.app.ui.theme.TestTaskDeliveryTheme
import com.example.app.ui.theme.UnSelectedBottomItem
import com.example.app.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTaskDeliveryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

                    val viewModel: HomeViewModel = hiltViewModel()
                    val scaffoldState = rememberScaffoldState()

                    Scaffold(snackbarHost = { scaffoldState.snackbarHostState },
                        bottomBar = {
                            AnimatedVisibility(
                                visible = bottomBarState.value,
                                enter = slideInVertically(initialOffsetY = { it }),
                                exit = slideOutVertically(targetOffsetY = { it })
                            ) {
                                BottomNavigation(backgroundColor = Color.White) {
                                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                                    val currentDestination = navBackStackEntry?.destination
                                    Screens.SCREEN_LIST.forEach { screen ->
                                        BottomNavigationItem(
                                            icon = {
                                                Icon(
                                                    screen.icon,
                                                    contentDescription = screen.route
                                                )
                                            },
                                            label = {
                                                Text(
                                                    screen.label,
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                            },
                                            selectedContentColor = MaterialTheme.colorScheme.primary,
                                            unselectedContentColor = UnSelectedBottomItem,
                                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                            onClick = {
                                                navController.navigate(screen.route) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            }
                                        )

                                    }
                                }
                            }
                        }) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    bottom = paddingValues.calculateBottomPadding(),
                                    top = paddingValues.calculateTopPadding()
                                )
                        ) {
                            Navigation(navController, viewModel, scaffoldState)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var selectedItem by remember {
            mutableStateOf(Constants.CITY_LIST[0])
        }
        Spinner(
            modifier = Modifier.wrapContentSize(),
            dropDownModifier = Modifier.wrapContentSize(),
            items = Constants.CITY_LIST,
            selectedItem = selectedItem,
            onItemSelected = {selectedItem = it},
            selectedItemFactory = { modifier, item ->
                Row(
                    modifier = modifier
                        .padding(8.dp)
                        .wrapContentSize()
                ) {
                    Text(text = item.toString())

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription ="drop down arrow"
                    )
                }
            },
            dropdownItemFactory = { item, _ ->
                Text(text = item.toString())
            }
        )
//        IconButton(onClick = { }) {
//            Image(
//                imageVector = Icons.Default.ArrowBack,
//                contentDescription = stringResource(R.string.back)
//            )
//        }
        IconButton(onClick = { }) {
            Image(
                imageVector = Icons.Default.QrCodeScanner,
                contentDescription = stringResource(R.string.back)
            )
        }
    }
}