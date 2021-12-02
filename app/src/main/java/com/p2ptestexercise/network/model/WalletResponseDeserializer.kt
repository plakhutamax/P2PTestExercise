package com.p2ptestexercise.network.model

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class WalletResponseDeserializer : JsonDeserializer<WalletResponse> {
    companion object {
        private const val KEY_RESULT = "result"
        private const val KEY_ERROR = "error"
        private const val KEY_VALUE = "value"
        private const val KEY_MESSAGE = "message"
        private const val KEY_CODE = "code"
    }

    // Not very pretty, but we shouldn't expose this custom gson, tided inner use only
    private val gson = GsonBuilder().registerTypeAdapter(
        Wallet::class.java,
        WalletDeserializer()
    ).create()

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): WalletResponse {
        if (json == null) {
            return WalletResponse.Error(
                message = "Invalid answer",
                code = -10000
            )
        }

        val root = json.asJsonObject
        if (root.has(KEY_ERROR)) {
            val error = root.getAsJsonObject(KEY_ERROR)
            return WalletResponse.Error(
                error.get(KEY_MESSAGE).asString,
                error.get(KEY_CODE).asInt
            )
        }

        val serializedList = json.asJsonObject
            .get(KEY_RESULT).asJsonObject
            .getAsJsonArray(KEY_VALUE).toString()

        return try {
            WalletResponse.Success(
                gson.fromJson<List<Wallet>>(serializedList).orEmpty()
            )
        } catch (e: Throwable) {
            WalletResponse.Error(
                message = e.localizedMessage ?: "Unknown error",
                code = -10001
            )
        }
    }

    private inline fun <reified T> Gson.fromJson(json: String?): T? {
        val type = object : TypeToken<T>() {}.type
        return fromJson(json, type)
    }
}