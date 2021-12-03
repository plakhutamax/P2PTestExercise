package com.p2ptestexercise.di

import com.p2ptestexercise.domain.mainactivity.MainActivityAccountInteractor
import com.p2ptestexercise.domain.mainactivity.MainActivityAccountInteractorImpl
import com.p2ptestexercise.ui.activity.*
import org.koin.dsl.module

object ActivityModule : ModuleFactory {
    override fun create() = module {
        scope<MainActivity> {
            factory<MainActivityAccountInteractor> {
                MainActivityAccountInteractorImpl(
                    authRepository = get()
                )
            }
            scoped<MainActivityContract.Presenter> { MainActivityPresenter(interactor = get()) }
        }
    }
}