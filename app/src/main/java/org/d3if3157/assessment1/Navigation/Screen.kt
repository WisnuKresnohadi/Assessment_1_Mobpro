package org.d3if3157.assessment1.Navigation

sealed class Screen(val  route: String) {
    data object Login:Screen("Login")
    data object Home:Screen("Home")
    data object About:Screen("About")
}