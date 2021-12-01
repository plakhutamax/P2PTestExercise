package com.p2ptestexercise.di

import com.p2ptestexercise.domain.login.AuthInteractor
import com.p2ptestexercise.domain.login.AuthInteractorImpl
import com.p2ptestexercise.ui.login.LoginContract
import com.p2ptestexercise.ui.login.LoginPresenter
import com.p2ptestexercise.ui.login.LoginScreen
import org.koin.dsl.module

object LoginModule : ModuleFactory {
    override fun create() = module {
        scope<LoginScreen> {
            scoped<AuthInteractor> { AuthInteractorImpl(repository = get()) }
            scoped<LoginContract.Presenter> { LoginPresenter(authInteractor = get()) }
        }
    }
}