package com.p2ptestexercise.ui.login

import com.p2ptestexercise.R
import com.p2ptestexercise.domain.login.AuthInteractor
import com.p2ptestexercise.ui.BasePresenter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
class LoginPresenter(
    private val authInteractor: AuthInteractor
) : BasePresenter<LoginContract.View, LoginContract.Router>(),
    LoginContract.Presenter {

    companion object {
        private const val TEXT_DEBOUNCE = 200L
    }

    private val textUpdates: MutableStateFlow<String> = MutableStateFlow("")

    init {
        textUpdates.debounce(TEXT_DEBOUNCE)
            .onEach(::onPassphraseChanged)
            .launchIn(this)
    }

    override fun passphraseUpdated(passphrase: String) {
        textUpdates.tryEmit(passphrase)
    }

    override fun signIn(passphrase: String) {
        // In common case we should start progress here, but account creation doesn't
        // involve any long running tasks

        // We don't have any particular requirements about error handling
        if (!authInteractor.validatePassphrase(passphrase)) {
            view.showError(R.string.seed_phrase_error_wrong_phrase)
            return
        }

        try {
            authInteractor.createAccount(passphrase)
        } catch (e: Exception) {
            val msg = e.localizedMessage
            if (msg != null) {
                view.showError(msg)
            } else {
                view.showError(R.string.seed_phrase_error_generic)
            }
            return
        }

        router.navigateToWallets()
    }

    private fun onPassphraseChanged(passphrase: String) {
        // First idea was to show how many words remaining, but it might be security violation
        // So just show error "Wrong passphrase" in case of error and it will keep exact number
        // of words in secret
        // Also it's a bit easier: we don't need dynamic hint
        view.hideError()
        if (passphrase.isBlank()) {
            view.showHelper(R.string.seed_phrase_hint)
        } else {
            view.hideHelper()
        }
    }
}