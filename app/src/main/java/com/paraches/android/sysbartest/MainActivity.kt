package com.paraches.android.sysbartest

import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paraches.android.sysbartest.ui.theme.SysBarTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SysBarTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )  {
                        Greeting("Android")
                        DialogSample()
                    }
                }
            }
        }

        window.insetsController?.apply {
            hide(android.view.WindowInsets.Type.navigationBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogSample() {
    val openDialog = remember { mutableStateOf(false)  }

    if (openDialog.value) {
        Dialog(
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = true
            ),
            onDismissRequest = { openDialog.value = false }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
            ) {
                val systemUIController = rememberSystemUiController()
                systemUIController.isNavigationBarVisible = false
                systemUIController.isStatusBarVisible = false
                Column(modifier = Modifier.padding(all = 16.dp)) {
                    Text(text = "Dialog without System bar.")
                    Button(onClick = { openDialog.value = false }) {
                        Text(text = "Hide dialog")
                    }
                }
            }
        }
    }

    Button(onClick = { openDialog.value = true }) {
        Text(text = "Show dialog")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SysBarTestTheme {
        Greeting("Android")
    }
}