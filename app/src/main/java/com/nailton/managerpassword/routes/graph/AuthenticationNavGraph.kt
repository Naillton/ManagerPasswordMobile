package com.nailton.managerpassword.routes.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nailton.managerpassword.routes.NavigationRoutes
import com.nailton.managerpassword.screens.authentication.LoginConfig
import com.nailton.managerpassword.screens.authentication.LoginScreen
import com.nailton.managerpassword.screens.authentication.RegisterScreen

fun NavGraphBuilder.authNavigation(navHostController: NavHostController) {
    navigation(
        route = RootNavGraph.Graph.AUTHENTICATION,
        startDestination = NavigationRoutes.Login.routes
    ) {
        composable(NavigationRoutes.Login.routes) {
            LoginScreen().Login(navController = navHostController, loginConfig = LoginConfig())
        }

        composable(NavigationRoutes.Register.routes) {
            RegisterScreen().Register(navController = navHostController)
        }
    }
}