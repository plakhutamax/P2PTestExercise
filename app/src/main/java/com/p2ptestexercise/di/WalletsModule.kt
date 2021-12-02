package com.p2ptestexercise.di

import com.p2ptestexercise.domain.wallet.WalletInteractor
import com.p2ptestexercise.domain.wallet.WalletInteractorImpl
import com.p2ptestexercise.network.WalletApi
import com.p2ptestexercise.ui.wallets.WalletsAdapter
import com.p2ptestexercise.ui.wallets.WalletsContract
import com.p2ptestexercise.ui.wallets.WalletsPresenter
import com.p2ptestexercise.ui.wallets.WalletsScreen
import org.koin.dsl.module
import retrofit2.Retrofit

object WalletsModule : ModuleFactory {
    override fun create() = module {
        scope<WalletsScreen> {
            scoped<WalletApi> { get<Retrofit>().create(WalletApi::class.java) }
            scoped<WalletInteractor> { WalletInteractorImpl(walletApi = get()) }
            scoped<WalletsContract.Presenter> { WalletsPresenter(walletInteractor = get()) }
            scoped { WalletsAdapter() }
        }
    }
}