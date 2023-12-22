package com.nailton.managerpassword.routes

sealed class NavigationRoutes(val routes: String) {
    object Login: NavigationRoutes("login")
    object Register: NavigationRoutes("register")
    object Home: NavigationRoutes("home")
    object Profile: NavigationRoutes("profile")
}
