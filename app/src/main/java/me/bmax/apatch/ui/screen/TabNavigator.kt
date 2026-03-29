package me.bmax.apatch.ui.screen

interface TabNavigator {
    fun navigate(route: String)
    fun popBackStack(): Boolean
    fun navigateUp(): Boolean
}
