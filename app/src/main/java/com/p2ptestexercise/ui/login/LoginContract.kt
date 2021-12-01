package com.p2ptestexercise.ui.login

import androidx.annotation.StringRes
import com.p2ptestexercise.ui.IPresenter
import com.p2ptestexercise.ui.IRouter
import com.p2ptestexercise.ui.IView

interface LoginContract {
    interface View : IView {
        var isAuthenticationInProgress: Boolean

        fun hideHelper()
        fun showHelper(@StringRes textRes: Int)
        fun showError(@StringRes textRes: Int)
        fun showError(text: String)
        fun hideError()
    }

    interface Presenter : IPresenter<View, Router> {
        fun passphraseUpdated(passphrase: String)
        fun signIn(passphrase: String)
    }

    interface Router : IRouter {
        fun navigateToWallets()
    }

}