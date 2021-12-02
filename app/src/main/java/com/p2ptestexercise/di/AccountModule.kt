package com.p2ptestexercise.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.p2ptestexercise.domain.account.AccountLocalStorage
import com.p2ptestexercise.domain.account.AccountStorage
import com.p2ptestexercise.domain.account.AuthRepository
import com.p2ptestexercise.domain.account.AuthRepositoryImpl
import org.koin.dsl.module

object AccountModule: ModuleFactory {

    private const val SHARED_PREFERENCES_NAME = "encrypted_preferences"

    override fun create() = module {
        factory<SharedPreferences> {
            val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            EncryptedSharedPreferences.create(
                SHARED_PREFERENCES_NAME,
                masterKey,
                get(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
        factory<AccountStorage> { AccountLocalStorage(sharedPreferences = get()) }
        single<AuthRepository> { AuthRepositoryImpl(storage = get()) }
    }
}