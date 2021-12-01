package com.p2ptestexercise.domain.login

import com.p2ptestexercise.model.solanaj.Account

interface AccountStorage {
    fun save(account: Account)
    fun read(): Account?
}