package com.p2ptestexercise.network.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class WalletDeserializer : JsonDeserializer<Wallet> {

    companion object {
        private const val KEY_ACCOUNT = "account"
        private const val KEY_PUBKEY = "pubkey"
        private const val KEY_DATA = "data"
        private const val KEY_PARSED = "parsed"
        private const val KEY_INFO = "info"
        private const val KEY_MINT = "mint"
        private const val KEY_TOKEN_AMONT = "tokenAmount"
        private const val KEY_UI_AMOUNT_STRING = "uiAmountString"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Wallet {
        check(json != null)
        val root = json.asJsonObject

        val pubkey = root.getAsJsonPrimitive(KEY_PUBKEY).toString()
        val accountInfo = root.getAsJsonObject(KEY_ACCOUNT)
            .getAsJsonObject(KEY_DATA)
            .getAsJsonObject(KEY_PARSED)
            .getAsJsonObject(KEY_INFO)

        val mint = accountInfo.getAsJsonPrimitive(KEY_MINT).toString()
        val amountString = accountInfo.getAsJsonObject(KEY_TOKEN_AMONT)
            .getAsJsonPrimitive(KEY_UI_AMOUNT_STRING)
            .toString()

        return Wallet(
            publicKey = pubkey,
            mint = mint,
            amount = amountString
        )
    }
}