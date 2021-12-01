package com.p2ptestexercise.domain.login

interface AuthInteractor {
    fun validatePassphrase(passphrase: String): Boolean
    @Throws fun createAccount(passphrase: String)
}