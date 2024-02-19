
package com.nailton.managerpassword

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.nailton.managerpassword.routes.graph.RootNavGraph
import com.nailton.managerpassword.ui.theme.ManagerPasswordTheme
import com.nailton.managerpassword.ui.theme.firstColor
import com.nailton.managerpassword.ui.theme.secondColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManagerPasswordTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(secondColor, firstColor))),
        Alignment.Center,
    ) {
        RootNavGraph().RootNavigationGraph(navHostController = rememberNavController())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ManagerPasswordTheme {
        MainScreen()
    }
}