package com.p2ptestexercise.di

import com.p2ptestexercise.ui.wallets.WalletsContract
import com.p2ptestexercise.ui.wallets.WalletsPresenter
import com.p2ptestexercise.ui.wallets.WalletsScreen
import org.koin.dsl.module

object WalletsModule : ModuleFactory {
    override fun create() = module {
        scope<WalletsScreen> {
            scoped<WalletsContract.Presenter> { WalletsPresenter() }
        }
    }
}