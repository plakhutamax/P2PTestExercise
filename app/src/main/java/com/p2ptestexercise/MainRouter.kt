package com.p2ptestexercise

interface MainRouter {

    enum class Destination {
        Wallets
    }

    fun navigateTo(destination: Destination)
}