package com.p2ptestexercise.domain.account

import com.p2ptestexercise.model.solanaj.Account

interface AccountStorage {
    fun save(account: Account)
    fun read(): Account?
}