package com.kch.navtestlab.ui.navigation


interface NavDestination {
    val route: String
}

object Home : NavDestination {
    override val route = "home"
}

object Order : NavDestination {
    override val route = "order"
}

object Product : NavDestination {
    override val route = "product"
}