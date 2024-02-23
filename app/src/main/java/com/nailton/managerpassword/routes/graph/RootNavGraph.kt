package com.nailton.managerpassword.routes.graph

import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nailton.managerpassword.screens.authenticated.Authenticated

class RootNavGraph {
    @Composable
    fun RootNavigationGraph(
        navHostController: NavHostController,
    ) {
        NavHost(
            navController = navHostController,
            route = Graph.ROOT,
            startDestination = Graph.AUTHENTICATION
        ) {
            authNavigation(navHostController)
            composable(route = Graph.AUTHENTICATED) {
                Authenticated().AuthenticatedBottom()
            }
        }
    }

    object Graph {
        const val ROOT = "root_graph"
        const val AUTHENTICATION = "auth_graph"
        const val AUTHENTICATED = "authenticated_graph"
    }
}