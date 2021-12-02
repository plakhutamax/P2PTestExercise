package com.p2ptestexercise.domain.account

import android.content.SharedPreferences
import android.util.Base64
import androidx.core.content.edit
import com.p2ptestexercise.model.solanaj.Account

class AccountLocalStorage(
    private val sharedPreferences: SharedPreferences
) : AccountStorage {

    companion object {
        private const val KEY_SECRET_KEY = "secret_key"
    }

    override fun save(account: Account) {
        val sk = account.secretKey
        sharedPreferences.edit {
            putString(KEY_SECRET_KEY, Base64.encodeToString(sk, Base64.DEFAULT))
        }
    }

    override fun read(): Account? {
        val sk = sharedPreferences.getString(KEY_SECRET_KEY, null)
        return sk?.let {
            Account(Base64.decode(it, Base64.DEFAULT))
        }
    }
}