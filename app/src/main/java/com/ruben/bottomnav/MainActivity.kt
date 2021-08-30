package com.ruben.bottomnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ruben.bottomnav.ui.theme.BottomNavTheme

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

@Composable
fun HomeScreen(showSheet: () -> Unit, showFeed: () -> Unit, showAnotherSheet: () -> Unit) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Body")
        Button(onClick = showSheet) {
            Text("Show sheet!")
        }
        Button(onClick = showFeed) {
            Text("Go to multiple bottom sheets composable!")
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