package com.nailton.managerpassword.routes.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nailton.managerpassword.routes.NavigationRoutes
import com.nailton.managerpassword.screens.authenticated.Authenticated
import com.nailton.managerpassword.screens.authenticated.HomeScreen
import com.nailton.managerpassword.screens.authenticated.ProfileScreen

fun NavGraphBuilder.authenticatedNavGraph(navController: NavHostController) {
    navigation(
        route = RootNavGraph.Graph.AUTHENTICATED,
        startDestination = NavigationRoutes.Home.routes
    ) {
        composable(NavigationRoutes.Home.routes) {
            Authenticated().AuthenticatedNav(navController = navController)
        }
    }
}  
