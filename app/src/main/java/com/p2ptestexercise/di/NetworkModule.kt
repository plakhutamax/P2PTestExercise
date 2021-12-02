package com.p2ptestexercise.di

import com.google.gson.GsonBuilder
import com.p2ptestexercise.network.RpcRequestInteractor
import com.p2ptestexercise.network.RpcRequestInteractorImpl
import com.p2ptestexercise.network.RpcRequestInterceptor
import com.p2ptestexercise.network.model.Wallet
import com.p2ptestexercise.network.model.WalletDeserializer
import com.p2ptestexercise.network.model.WalletResponse
import com.p2ptestexercise.network.model.WalletResponseDeserializer
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object NetworkModule : ModuleFactory {

    private const val KEY_BASE_URL = "base_url"
    private const val KEY_JSON_MEDIA_TYPE = "json_media_type"
    private const val KEY_RPC_INTERCEPTOR = "rpc_interceptor"

    private const val BASE_URL = "https://api.devnet.solana.com/"

    @OptIn(ExperimentalSerializationApi::class)
    override fun create() = module {

        // Can be injected from wherever you like, for our purpose const is OK
        single(named(KEY_BASE_URL)) { BASE_URL }
        single(named(KEY_JSON_MEDIA_TYPE)) { MediaType.get("application/json") }

        single {
            GsonBuilder().registerTypeAdapter(
                WalletResponse::class.java,
                WalletResponseDeserializer()
            ).registerTypeAdapter(
                Wallet::class.java,
                WalletDeserializer()
            ).create()
        }
        factory<RpcRequestInteractor> { RpcRequestInteractorImpl(authRepository = get()) }
        factory<Interceptor>(named(KEY_RPC_INTERCEPTOR)) {
            RpcRequestInterceptor(
                requestInteractor = get(),
                mediaType = get(named(KEY_JSON_MEDIA_TYPE))
            )
        }
        factory {
            OkHttpClient.Builder()
                .addInterceptor(get(named(KEY_RPC_INTERCEPTOR)))
                .build()
        }

        single {
            Retrofit.Builder()
                .client(get())
                .baseUrl(get<String>(named(KEY_BASE_URL)))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(get()))
//                .addConverterFactory(Json.asConverterFactory(get(named(KEY_JSON_MEDIA_TYPE))))
                .build()
        }
    }
}