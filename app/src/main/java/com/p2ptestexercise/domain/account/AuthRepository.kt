package com.p2ptestexercise.domain.account

import com.p2ptestexercise.model.solanaj.Account

typealias Passphrase = List<String> // It could be value class, but we don't have any mangling issues here

interface AuthRepository {
    fun isAccountCreated(): Boolean
    fun getAccount(): Account?
    fun createAccount(passwords: Passphrase): Account
}