package com.p2ptestexercise.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object NetworkModule: ModuleFactory {

    private const val KEY_BASE_URL = "base_url"
    private const val KEY_JSON_MEDIA_TYPE = "json_media_type"

    private const val BASE_URL = "https://api.devnet.solana.com/"

    @OptIn(ExperimentalSerializationApi::class)
    override fun create() = module {

        // Can be injected from wherever you like, for our purpose const is OK
        single(named(KEY_BASE_URL)) { BASE_URL }
        single(named(KEY_JSON_MEDIA_TYPE)) { MediaType.get("application/json") }

        single {
            Retrofit.Builder()
                .baseUrl(get<String>(named(KEY_BASE_URL)))
                .addConverterFactory(Json.asConverterFactory(get(named(KEY_JSON_MEDIA_TYPE))))
                .build()
        }
    }
}