package com.nailton.managerpassword.routes.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person

object NavBarItens {

    val barItens = listOf(
        BarItem(
            title = "Home",
            image = Icons.Outlined.Home,
            route = "home"
        ),

        BarItem(
            title = "Profile",
            image = Icons.Outlined.Person,
            route = "profile"
        )
    )
}