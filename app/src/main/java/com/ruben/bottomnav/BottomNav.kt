package com.ruben.bottomnav

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ruben.bottomnav.BottomComposable
import com.ruben.bottomnav.BottomSheeet
import com.ruben.bottomnav.BottomSheet
import com.ruben.bottomnav.HomeScreen
import java.util.*

/**
 * Created by Ruben Quadros on 30/08/21
 **/
private object Destinations {
    const val Home = "HOME"
    const val Feed = "FEED"
    const val Sheet = "SHEET"
    const val Sheeet = "SHEEET"
}

@ExperimentalMaterialNavigationApi
@Composable
fun BottomNav() {
    val navController = rememberNavController()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

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
            composable(Destinations.Feed) {
                ModalBottomSheetLayout(bottomSheetNavigator) {
                    BottomComposable(
                        showSheet = {
                            navController.navigate(Destinations.Sheet + "?arg=From Home Screen")
                        },
                        showAnotherSheet = {
                            navController.navigate(Destinations.Sheeet)
                        }
                    )
                }
            }
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