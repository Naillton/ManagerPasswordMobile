package com.nailton.managerpassword.screens.authenticated

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
    @OptIn(ExperimentalMaterial3Api::class)
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
                HomeScreen().Home()
            }

            composable(NavigationRoutes.Profile.routes) {
                ProfileScreen().Profile()
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        NavigationBar(
            containerColor = Color.Transparent
        ) {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            NavBarItens.barItens.forEach {
                NavigationBarItem(
                    selected = currentRoute == it.route,
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
                        Icon(imageVector = it.image, contentDescription = it.title, tint = Color.Black)
                    },
                    label = {
                        Text(text = it.title, color = Color.Black)
                    }
                )
            }
        }
    }
}