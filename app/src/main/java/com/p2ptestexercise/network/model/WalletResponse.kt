package com.p2ptestexercise.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * It's just one of many approaches
 */
sealed class WalletResponse {
    data class Error(val message: String, val code: Int) : WalletResponse()
    data class Success(
        val wallets: List<Wallet>
    ) : WalletResponse()
}

data class Wallet(
    val publicKey: String,
    val mint: String,
    val amount: String
)

