package com.p2ptestexercise.ui

interface MainRouter {

    enum class Destination {
        Wallets, Login
    }

    fun navigateTo(destination: Destination)
}