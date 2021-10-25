package com.ruben.bottomnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ruben.bottomnav.ui.theme.BottomNavTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialNavigationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            BottomNavTheme {
                ProvideWindowInsets {
                    BottomNav()
                }
            }
        }

        WindowInsetsControllerCompat(window, findViewById(R.id.content)).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}

@Composable
fun HomeScreen(showSheet: () -> Unit, showFeed: () -> Unit, showAnotherSheet: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally) {
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

    Column(modifier = Modifier.navigationBarsWithImePadding()) {
        Text("Sheet with arg: $arg")

        Text("Below is a text input field and below that there is a large text")

        SearchBar()

                Text(
            text = "Generating random paragraphs can be an excellent way for writers to get their creative flow going at the beginning of the day. The writer has no idea what topic the random paragraph will be about when it appears. This forces the writer to use creativity to complete one of three common writing challenges. The writer can use the paragraph as the first one of a short story and build upon it. A second option is to use the random paragraph somewhere in a short story they create. The third option is to have the random paragraph be the ending paragraph in a short story. No matter which of these challenges is undertaken, the writer is forced to use creativity to incorporate the paragraph into their writing. A random paragraph can also be an excellent way for a writer to tackle writers' block. Writing block can often happen due to being stuck with a current project that the writer is trying to complete. By inserting a completely random paragraph from which to begin, it can take down some of the issues that may have been causing the writers' block in the first place. Another productive way to use this tool to begin a daily writing routine. One way is to generate a random paragraph with the intention to try to rewrite it while still keeping the original meaning. The purpose here is to just get the writing started so that when the writer goes onto their day's writing projects, words are already flowing from their fingers. Another writing challenge can be to take the individual sentences in the random paragraph and incorporate a single sentence from that into a new paragraph to create a short story. Unlike the random sentence generator, the sentences from the random paragraph will have some connection to one another so it will be a bit different. You also won't know exactly how many sentences will appear in the random paragraph. It's not only writers who can benefit from this free online tool. If you're a programmer who's working on a project where blocks of text are needed, this tool can be a great way to get that. It's a good way to test your programming and that the tool being created is working well.\n" +
                    "\n"
        )

        Button(onClick = showFeed) {
            Text("Click me to navigate!")
        }
        Button(onClick = showAnotherSheet) {
            Text("Click me to show another sheet!")
        }
    }
}

@Composable
fun BottomSheeet() {
    Text("This is a new sheeet")
}

@Composable
fun BottomComposable(showSheet: () -> Unit, showAnotherSheet: () -> Unit) {
    var textState by remember {
        mutableStateOf(TextFieldValue())
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .imePadding()
        ) {
        Column(modifier = Modifier.align(Alignment.TopCenter)) {
            Button(onClick = showSheet) {
                Text("Click me to show a sheet!")
            }
            Button(onClick = showAnotherSheet) {
                Text("Click me to show another sheet!")
            }
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Box(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.Red))

            TextField(
                value = textState,
                onValueChange = {textState = it}
            )
        }
    }

}

@Composable
fun SearchBar() {

    var textState by remember {
        mutableStateOf(TextFieldValue())
    }

    val focusManager = LocalFocusManager.current
    TextField(
        value = textState, onValueChange = {textState = it},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Blue
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        )
    )
}