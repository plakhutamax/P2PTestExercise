package com.p2ptestexercise.domain.wallet

import com.p2ptestexercise.network.WalletApi
import com.p2ptestexercise.network.model.Wallet
import com.p2ptestexercise.network.model.WalletResponse

class WalletFetchException(message: String) : Exception(message)

class WalletInteractorImpl(
    private val walletApi: WalletApi
) : WalletInteractor {

    companion object {
        private const val ENCODING = "jsonParseddsfdsf"
    }

    override suspend fun fetchWallets(): List<Wallet> {
        return when (val response = walletApi.getWallets(ENCODING)) {
            is WalletResponse.Error -> throw WalletFetchException(response.message)
            is WalletResponse.Success -> response.wallets
        }
    }
}