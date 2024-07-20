package com.nailton.managerpassword.screens.authenticated

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nailton.managerpassword.routes.NavigationRoutes
import com.nailton.managerpassword.routes.bottomBar.NavBarItens
import com.nailton.managerpassword.routes.graph.RootNavGraph
import com.nailton.managerpassword.ui.theme.firstColor
import com.nailton.managerpassword.ui.theme.secondColor

class Authenticated {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun AuthenticatedBottom(navController: NavHostController = rememberNavController()) {
        Scaffold(
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    secondColor,
                                    firstColor
                                )
                            )
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    AuthenticatedNav(navController = navController)
                }
            },
            bottomBar = {
                contentColorFor(backgroundColor = Color.Yellow)
                BottomNavigationBar(navController = navController)
            }
        )
    }

    @Composable
    fun AuthenticatedNav(navController: NavHostController) {
        NavHost(
            navController = navController,
            route = RootNavGraph.Graph.AUTHENTICATED,
            startDestination = NavigationRoutes.Home.routes
        ) {
            composable(NavigationRoutes.Home.routes) {
                HomeScreen().HomeConfig(navController = navController)
            }

            composable(NavigationRoutes.Profile.routes) {
                ProfileScreen().Profile()
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        NavigationBar(
            containerColor = Color.Transparent,
            contentColor = Color.Cyan
        ) {
            //val backStackEntry by navController.currentBackStackEntryAsState()
            //val currentRoute = backStackEntry?.destination?.route

            NavBarItens.barItens.forEach {
                contentColorFor(backgroundColor = Color.Magenta)
                NavigationBarItem(
                    //selected = currentRoute == it.route,
                    selected = false,
                    onClick = {
                        navController.navigate(it.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        contentColorFor(backgroundColor = Color.Blue)
                        Icon(
                            imageVector = it.image,
                            contentDescription = it.title,
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(text = it.title, color = Color.White)
                    }
                )
            }
        }
    }
}