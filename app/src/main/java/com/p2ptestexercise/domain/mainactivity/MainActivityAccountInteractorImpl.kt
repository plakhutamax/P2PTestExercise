package com.p2ptestexercise.domain.mainactivity

import com.p2ptestexercise.domain.account.AuthRepository

class MainActivityAccountInteractorImpl(
    private val authRepository: AuthRepository
) : MainActivityAccountInteractor {

    override fun isAccountLoaded() = authRepository.isAccountCreated()
}