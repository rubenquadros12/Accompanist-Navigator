package com.ruben.bottomnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ruben.bottomnav.ui.theme.BottomNav
import com.ruben.bottomnav.ui.theme.BottomNavTheme
import java.util.*

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialNavigationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BottomNavTheme {
                BottomNav()
            }
        }
    }
}

/*@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun BottomSheetNavDemo() {
    val navController = rememberNavController()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(navController, Destinations.Home) {
            composable(Destinations.Home) {
                HomeScreen(
                    showSheet = {
                        navController.navigate(Destinations.Sheet + "?arg=From Home Screen")
                    },
                    showFeed = { navController.navigate(Destinations.Feed) },
                    showAnotherSheet = { navController.navigate(Destinations.Sheeet) }
                )
            }
            composable(Destinations.Feed) { Text("Feed!") }
            bottomSheet(Destinations.Sheet + "?arg={arg}") { backstackEntry ->
                val arg = backstackEntry.arguments?.getString("arg") ?: "Missing argument :("
                BottomSheet(
                    showFeed = { navController.navigate(Destinations.Feed) },
                    showAnotherSheet = {
                        navController.navigate(Destinations.Sheet + "?arg=${UUID.randomUUID()}")
                    },
                    arg = arg
                )
            }
            bottomSheet(Destinations.Sheeet) {
                BottomSheeet()
            }
        }
    }
}*/

@Composable
fun HomeScreen(showSheet: () -> Unit, showFeed: () -> Unit, showAnotherSheet: () -> Unit) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Body")
        Button(onClick = showSheet) {
            Text("Show sheet!")
        }
        Button(onClick = showFeed) {
            Text("Navigate to Feed")
        }
        Button(onClick = showAnotherSheet) {
            Text("Click me to show another sheet!")
        }
    }
}

@Composable
fun BottomSheet(showFeed: () -> Unit, showAnotherSheet: () -> Unit, arg: String) {
    Text("Sheet with arg: $arg")
    Button(onClick = showFeed) {
        Text("Click me to navigate!")
    }
    Button(onClick = showAnotherSheet) {
        Text("Click me to show another sheet!")
    }
}

@Composable
fun BottomSheeet() {
    Text("This is a new sheeet")
}

@Composable
fun BottomComposable(showSheet: () -> Unit, showAnotherSheet: () -> Unit) {
    Column {
        Button(onClick = showSheet) {
            Text("Click me to show a sheet!")
        }
        Button(onClick = showAnotherSheet) {
            Text("Click me to show another sheet!")
        }
    }
}