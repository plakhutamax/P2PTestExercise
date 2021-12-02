package com.p2ptestexercise.domain.wallet

import com.p2ptestexercise.network.model.Wallet

interface WalletInteractor {
    suspend fun fetchWallets(): List<Wallet>
}