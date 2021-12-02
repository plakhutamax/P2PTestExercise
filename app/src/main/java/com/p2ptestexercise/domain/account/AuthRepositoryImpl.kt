package com.p2ptestexercise.domain.account


import com.p2ptestexercise.model.solanaj.Account

class AuthRepositoryImpl(
    private val storage: AccountStorage
) : AuthRepository {

    private var account: Account? = storage.read()

    override fun isAccountCreated() = account != null

    override fun getAccount() = account

    override fun createAccount(passwords: Passphrase): Account {
        return Account.fromBip32Mnemonic(passwords).also {
            account = it
            storage.save(it)
        }
    }
}